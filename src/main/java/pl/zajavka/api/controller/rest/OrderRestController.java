package pl.zajavka.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.FactDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.FactMapper;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping(OrderRestController.API_ORDER)
public class OrderRestController {

    public static final String API_ORDER = "/api/order";

    public static final String SUBMIT = "/submit";
    public static final String RESTAURANT_NAME = "/{restaurantName}";
    public static final String MENU_ITEM_NUMBER = "/{menuItemNumber}";
    public static final String USER_NAME = "/{userName}";
    public static final String CUSTOMER_ORDERS = "/customerPage/orders";
    public static final String DELETE = "/delete";
    public static final String ORDER_NUMBER = "/{orderNumber}";

    private final MenuItemService menuItemService;
    private final OrderService orderService;
    private final FoodOrderService foodOrderService;
    private final RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;
    private final FactsService factsService;
    private final FactMapper factMapper;

    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;

    private final Map<String, List<MenuItemDTO>> cart = new HashMap<>();

    @PostMapping(MENU_ITEM_NUMBER + USER_NAME)
    public String addToCart(
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

        return "Menu Item with number: [%s] added".formatted(menuItemNumber);
    }

    @DeleteMapping(MENU_ITEM_NUMBER + USER_NAME)
    public String deleteFromCart(
            @PathVariable String menuItemNumber,
            @PathVariable String userName
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        List<MenuItemDTO> cartMenuItemList = cart.get(userName);
        if (Objects.isNull(cartMenuItemList) || cartMenuItemList.isEmpty()) {
            return "There is no items in cart";
        }
        cartMenuItemList.remove(menuItemDTO);
        cart.put(userName, cartMenuItemList);

        return "Menu Item with number: [%s] removed".formatted(menuItemNumber);
    }

    @PostMapping(RESTAURANT_NAME + SUBMIT + USER_NAME)
    public ResponseEntity<Object> submitOrder(
            @PathVariable String restaurantName,
            @PathVariable String userName,
            @Valid @RequestBody AddressDTO addressDTO
    ) {
        if (!cart.containsKey(userName)) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body("There are no menu items in your shopping list!");
        }
        if (cart.get(userName).isEmpty()) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body("There are no menu items in your shopping list!");
        }
        Set<String> city = restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName);
        Set<String> street = restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName);
        if (restaurantDeliveryAddressMatchesCustomerAddress(addressDTO, city, street)) {
            OrderDTO orderDTO = orderMapper.mapToDTO(orderService.buildAndSaveOrder(restaurantName, userName, cart.get(userName)));
            cart.remove(userName);
            return ResponseEntity.ok().body(orderDTO);
        } else {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body("Your delivery address is probably not available for this restaurant's delivery address.");
        }
    }

    private boolean restaurantDeliveryAddressMatchesCustomerAddress(
            AddressDTO addressDTO,
            Set<String> city,
            Set<String> street
    ) {
        if (city.contains(addressDTO.getCity())) {
            return street.contains(addressDTO.getStreetName());
        }
        return false;
    }

    @GetMapping(CUSTOMER_ORDERS + USER_NAME)
    public List<OrderDTO> getCustomerOrders(
            @PathVariable String userName
    ) {

        return foodOrderService.availableFoodOrdersByUserName(userName)
                .stream().map(orderMapper::mapToDTO)
                .toList();
    }

    @GetMapping("/random/cat/fact")
    public FactDTO getRandomCatFact() {
        return factMapper.mapToDTO(factsService.getRandomCatFact());
    }

    @DeleteMapping(CUSTOMER_ORDERS + DELETE + ORDER_NUMBER)
    public ResponseEntity deleteOrder(
            @PathVariable String orderNumber
    ) {
        OffsetDateTime foodOrderReceivedDateTime = foodOrderService.findFoodOrderReceivedDateTime(orderNumber);

        if (Duration.between(foodOrderReceivedDateTime, OffsetDateTime.now()).toMinutes() < 1) {
            foodOrderService.deleteFoodOrder(orderNumber);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body("Unfortunately, it is not possible to delete this order." +
                            " It's been 20 minutes since it was submitted.");
        }
    }


}
