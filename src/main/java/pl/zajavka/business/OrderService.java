package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.domain.Order;
import pl.zajavka.domain.Restaurant;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final RestaurantService restaurantService;
    private final FoodOrderService foodOrderService;
    private final MenuItemFoodOrderService menuItemFoodOrderService;

    public List<Restaurant> availableRestaurants() {
        return restaurantService.findAvailable();
    }


//    public OrderDTO buildAndSaveOrder(String restaurantName, String userName, List<MenuItemDTO> cart) {
//        Order order = Order.builder()
//                .userName(userName)
//                .restaurantName(restaurantName)
//                .orderNumber(generateOrderNumber(userName))
//                .menuItemList(cart)
//                .receivedDateTime(OffsetDateTime.now())
//                .totalAmount(calculateTotalAmount(cart))
//                .build();
//
//        foodOrderService.saveFoodOrder(orderDTO, restaurantName, userName);
////        menuItemFoodOrderService.saveOrder(orderDTO);
//
//        return orderDTO;
//    }

    private BigDecimal calculateTotalAmount(List<MenuItemDTO> cart) {
        return cart.stream()
                .map(MenuItemDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String generateOrderNumber(String userName) {
        return userName + "-" + RandomStringUtils.randomAlphanumeric(10);
    }
}
