package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.MenuItemDAO;
import pl.zajavka.domain.MenuItem;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuItemService {

    private final MenuItemDAO menuItemDAO;

    public List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName) {
        return menuItemDAO.findAvailableMenuItemsByRestaurantName(restaurantName);
    }

    public String assignNumber(String restaurantName) {
        List<MenuItem> availableMenuItems = findAvailableMenuItemsByRestaurantName(restaurantName);
        return restaurantName + ": " + (availableMenuItems.size() + 1);
    }
}
