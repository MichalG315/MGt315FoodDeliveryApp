package pl.zajavka.business.dao;

import pl.zajavka.domain.MenuItem;

import java.util.List;

public interface MenuItemDAO {

    List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName);

    MenuItem saveMenuItem(MenuItem menuItem);

    void deleteAll();
}
