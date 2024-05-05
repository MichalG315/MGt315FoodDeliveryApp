package pl.zajavka.business.dao;

import pl.zajavka.domain.Order;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;

public interface MenuItemFoodOrderDAO {
    void saveMenuItemFoodOrder(Order order, FoodOrderEntity savedfoodOrderEntity);

    void deleteAll();
}
