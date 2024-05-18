package pl.zajavka.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    static final String ORDER = "/order";
    static final String SUBMIT = "/submit";
    static final String RESTAURANT_NAME = "/{restaurantName}";
    static final String MENU_ITEM_NUMBER = "/{menuItemNumber}";
    static final String USER_NAME = "/{userName}";
    static final String CUSTOMER_ORDERS = "/customerPage/orders";
    static final String DELETE = "/delete";
    static final String ORDER_NUMBER = "/{orderNumber}";

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final OrderService orderService;
    private final FoodOrderService foodOrderService;
    private final RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;
    private final FactsService factsService;

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;

    private final Map<String, List<MenuItemDTO>> cart = new HashMap<>();

    @GetMapping(ORDER + RESTAURANT_NAME + USER_NAME)
    public String showRestaurantPage(
            @PathVariable String restaurantName,
            @PathVariable String userName,
            Model model
    ) {

        RestaurantDTO restaurantDTO = restaurantMapper.mapToDTO(restaurantService.findByRestaurantName(restaurantName));

        List<MenuItemDTO> menuItemsByRestaurantName =
                menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName).stream()
                        .map(menuItemMapper::mapToDTO)
                        .toList();

        Set<String> streetNames = restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName);
        Set<String> cities = restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName);

        model.addAttribute("restaurantDTO", restaurantDTO);
        model.addAttribute("menuItemDTOs", menuItemsByRestaurantName);
        model.addAttribute("streetNames", streetNames);
        model.addAttribute("cities", cities);
        model.addAttribute("orderDTOs", cart.get(userName));
        model.addAttribute("addressDTO", new AddressDTO());

        return "order";
    }

    @PostMapping(ORDER + RESTAURANT_NAME + MENU_ITEM_NUMBER + USER_NAME)
    public String addToCart(
            @PathVariable String restaurantName,
            @PathVariable String menuItemNumber,
            @PathVariable String userName
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        if (!cart.containsKey(userName)) {
            cart.put(userName, new ArrayList<>(List.of(menuItemDTO)));
        } else {
            List<MenuItemDTO> cartMenuItemList = cart.get(userName);
            cartMenuItemList.add(menuItemDTO);
            cart.put(userName, cartMenuItemList);
        }

        return "redirect:/order/" + restaurantName + "/" + userName;
    }

    @DeleteMapping(ORDER + RESTAURANT_NAME + MENU_ITEM_NUMBER + USER_NAME)
    public String deleteFromCart(
            @PathVariable String restaurantName,
            @PathVariable String menuItemNumber,
            @PathVariable String userName
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        List<MenuItemDTO> cartMenuItemList = cart.get(userName);
        if (Objects.isNull(cartMenuItemList) || cartMenuItemList.isEmpty()) {
            return "redirect:/order/" + restaurantName + "/" + userName;
        }
        cartMenuItemList.remove(menuItemDTO);
        cart.put(userName, cartMenuItemList);

        return "redirect:/order/" + restaurantName + "/" + userName;
    }

    @PostMapping(ORDER + RESTAURANT_NAME + SUBMIT + USER_NAME)
    public String submitOrder(
            @PathVariable String restaurantName,
            @PathVariable String userName,
            @Valid @ModelAttribute("addressDTO") AddressDTO addressDTO
    ) {
        Set<String> city = restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName);
        Set<String> street = restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName);
        if (restaurantDeliveryAddressMatchesCustomerAddress(addressDTO, city, street)) {
            orderService.buildAndSaveOrder(restaurantName, userName, cart.get(userName));
            cart.remove(userName);
            return "redirect:" + CUSTOMER_ORDERS + "/" + userName;
        } else {
            return "error_customer_address";
        }
    }

    private boolean restaurantDeliveryAddressMatchesCustomerAddress(
            AddressDTO addressDTO,
            Set<String> city, Set<String> street) {
        if (city.contains(addressDTO.getCity())) {
            return street.contains(addressDTO.getStreetName());
        }
        return false;
    }

    @GetMapping(value = CUSTOMER_ORDERS + USER_NAME)
    public String getCustomerOrders(
            @PathVariable String userName,
            Model model
    ) {
        List<OrderDTO> availableCustomerOrders = foodOrderService.availableFoodOrdersByUserName(userName)
                .stream().map(orderMapper::mapToDTO)
                .toList();

        String randomCatFact = factsService.getRandomCatFact();

        model.addAttribute("availableCustomerOrderDTOs", availableCustomerOrders);
        model.addAttribute("randomCatFact", randomCatFact);

        return "customer_page_orders";
    }

    @DeleteMapping(value = CUSTOMER_ORDERS + DELETE + ORDER_NUMBER + USER_NAME)
    public String deleteOrder(
            @PathVariable String orderNumber,
            @PathVariable String userName
    ) {
        OffsetDateTime foodOrderReceivedDateTime = foodOrderService.findFoodOrderReceivedDateTime(orderNumber);

        if (Duration.between(foodOrderReceivedDateTime, OffsetDateTime.now()).toMinutes() < 20) {
            foodOrderService.deleteFoodOrder(orderNumber);
        } else {
            return "error_delete_order";
        }

        return "redirect:/customerPage/orders/" + userName;
    }
}
