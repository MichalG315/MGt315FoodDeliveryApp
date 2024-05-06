package pl.zajavka.business.dao;

import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemDAO {

    List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName);

    void saveMenuItem(MenuItem menuItem, String restaurantName);

    void deleteAll();

    Optional<MenuItem> findMenuItemByMenuItemNumber(String menuItemNumber);

}
