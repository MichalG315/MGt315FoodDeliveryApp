package pl.zajavka.business.dao;

import pl.zajavka.domain.Order;

import java.util.List;

public interface FoodOrderDAO {
    void saveFoodOrder(Order order, String restaurantName, String userName);

    void deleteAll();

    List<Order> availableFoodOrdersByUserName(String userName);

    void deleteOrder(String orderNumber);

    Order findFoodOrder(String orderNumber);
}
