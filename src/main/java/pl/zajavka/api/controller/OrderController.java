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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;

    private final List<MenuItemDTO> cart = new ArrayList<>();

    @GetMapping(ORDER + RESTAURANT_NAME)
    public String showRestaurantPage(
            @PathVariable String restaurantName,
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
        model.addAttribute("orderDTOs", cart);
        model.addAttribute("addressDTO", new AddressDTO());

        return "order";
    }

    @PostMapping(ORDER + RESTAURANT_NAME + MENU_ITEM_NUMBER)
    public String addToCart(
            @PathVariable String restaurantName,
            @PathVariable String menuItemNumber
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        cart.add(menuItemDTO);

        return "redirect:/order/" + restaurantName;
    }

    @DeleteMapping(ORDER + RESTAURANT_NAME + MENU_ITEM_NUMBER)
    public String deleteFromCart(
            @PathVariable String restaurantName,
            @PathVariable String menuItemNumber
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        cart.remove(menuItemDTO);

        return "redirect:/order/" + restaurantName;
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
            orderService.buildAndSaveOrder(restaurantName, userName, cart);
            cart.clear();
            return "redirect:" + CUSTOMER_ORDERS + "/" + userName;
        } else {
            return "error_customer_address";
        }
    }

    private boolean restaurantDeliveryAddressMatchesCustomerAddress(AddressDTO addressDTO, Set<String> city, Set<String> street) {
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

        model.addAttribute("availableCustomerOrderDTOs", availableCustomerOrders);

        return "customer_page_orders";
    }

    @DeleteMapping(value = CUSTOMER_ORDERS + DELETE + ORDER_NUMBER + USER_NAME)
    public String deleteMenuItem(
            @PathVariable String orderNumber,
            @PathVariable String userName
    ) {
        OffsetDateTime foodOrderReceivedDateTime = foodOrderService.findFoodOrderReceivedDateTime(orderNumber);

        if (Duration.between(foodOrderReceivedDateTime, OffsetDateTime.now()).toMinutes() < 1) {
            foodOrderService.deleteFoodOrder(orderNumber);
        } else {
            return "error_delete_order";
        }

        return "redirect:/customerPage/orders/" + userName;
    }
}
