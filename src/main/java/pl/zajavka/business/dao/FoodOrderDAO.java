package pl.zajavka.business.dao;

import pl.zajavka.domain.Order;

public interface FoodOrderDAO {
    void saveFoodOrder(Order order, String restaurantName, String userName);
}
