package pl.zajavka.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping(OrderRestController.API_ORDER)
public class OrderRestController {

    static final String API_ORDER = "/api/order";

    static final String SUBMIT = "/submit";
    static final String RESTAURANT_NAME = "/{restaurantName}";
    static final String MENU_ITEM_NUMBER = "/{menuItemNumber}";
    static final String USER_NAME = "/{userName}";
    static final String CUSTOMER_ORDERS = "/customerPage/orders";
    static final String DELETE = "/delete";
    static final String ORDER_NUMBER = "/{orderNumber}";

    private final MenuItemService menuItemService;
    private final OrderService orderService;
    private final FoodOrderService foodOrderService;
    private final RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;
    private final FactsService factsService;

    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;

    private final List<MenuItemDTO> cart = new ArrayList<>();

    @PostMapping(MENU_ITEM_NUMBER)
    public String addToCart(
            @PathVariable String menuItemNumber
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        cart.add(menuItemDTO);

        return "Menu Item with number: [%s] added".formatted(menuItemNumber);
    }

    @DeleteMapping(MENU_ITEM_NUMBER)
    public String deleteFromCart(
            @PathVariable String menuItemNumber
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        cart.remove(menuItemDTO);

        return "Menu Item with number: [%s] removed".formatted(menuItemNumber);
    }

    @PostMapping(RESTAURANT_NAME + SUBMIT + USER_NAME)
    public ResponseEntity submitOrder(
            @PathVariable String restaurantName,
            @PathVariable String userName,
            @Valid @ModelAttribute("addressDTO") AddressDTO addressDTO
    ) {
        Set<String> city = restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName);
        Set<String> street = restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName);
        if (restaurantDeliveryAddressMatchesCustomerAddress(addressDTO, city, street)) {
            orderService.buildAndSaveOrder(restaurantName, userName, cart);
            cart.clear();
            return ResponseEntity.ok().build();
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

    @GetMapping("random/cat/fact")
    public String getRandomCatFact() {
        return factsService.getRandomCatFact();
    }

    @DeleteMapping(CUSTOMER_ORDERS + DELETE + ORDER_NUMBER + USER_NAME)
    public ResponseEntity deleteOrder(
            @PathVariable String orderNumber
    ) {
        if (Objects.isNull(orderNumber)) {
            return ResponseEntity.notFound().build();
        }
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
