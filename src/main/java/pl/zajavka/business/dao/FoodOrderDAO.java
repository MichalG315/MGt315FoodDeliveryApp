package pl.zajavka.business.dao;

import pl.zajavka.domain.Order;

import java.time.OffsetDateTime;
import java.util.List;

public interface FoodOrderDAO {
    void saveFoodOrder(Order order, String restaurantName, String userName);

    void deleteAll();

    List<Order> availableFoodOrdersByUserName(String userName);

    List<Order> availableFoodOrdersByRestaurantName(String restaurantUserName);

    void deleteOrder(String orderNumber);

    Order findFoodOrder(String orderNumber);

    void setCompletedDateTime(String orderNumber, OffsetDateTime completedDateTime);
}
