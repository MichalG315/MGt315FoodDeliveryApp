package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.business.dao.MenuItemDAO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuItemService {

    private final MenuItemDAO menuItemDAO;

    public List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName) {
        return menuItemDAO.findAvailableMenuItemsByRestaurantName(restaurantName);
    }

    public MenuItem findMenuItemByMenuItemNumber(String menuItemNumber) {
        Optional<MenuItem> menuItem = menuItemDAO.findMenuItemByMenuItemNumber(menuItemNumber);
        if (menuItem.isEmpty()) {
            throw new NotFoundException("Could not find menu item with number: %s" .formatted(menuItemNumber));
        }
        return menuItem.get();
    }

    public String assignNumber(String restaurantName) {
        List<MenuItem> availableMenuItems = findAvailableMenuItemsByRestaurantName(restaurantName);
        return restaurantName.substring(0, 3) + (availableMenuItems.size() + 1);
    }
}
