package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.dao.MenuItemDAO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuItemService {

    private final UserService userService;
    private final RestaurantService restaurantService;

    private final MenuItemDAO menuItemDAO;

    @Transactional
    public List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName) {
        return menuItemDAO.findAvailableMenuItemsByRestaurantName(restaurantName);
    }

    @Transactional
    public MenuItem findMenuItemByMenuItemNumber(String menuItemNumber) {
        Optional<MenuItem> menuItem = menuItemDAO.findMenuItemByMenuItemNumber(menuItemNumber);
        if (menuItem.isEmpty()) {
            throw new NotFoundException("Could not find menu item with number: %s".formatted(menuItemNumber));
        }
        return menuItem.get();
    }

    public String assignNumber(String restaurantName) {
        List<MenuItem> availableMenuItems = findAvailableMenuItemsByRestaurantName(restaurantName);
        return restaurantName.substring(0, 3) + (availableMenuItems.size() + 1);
    }

    @Transactional
    public void saveNewMenuItem(MenuItem menuItem, String restaurantUserName) {
        Integer userId = userService.findUserId(restaurantUserName);
        String restaurantName = restaurantService.findRestaurantNameByUserId(userId);
        String menuItemNumber = assignNumber(restaurantName);
        menuItemDAO.saveMenuItem(menuItem.withMenuItemNumber(menuItemNumber), restaurantName);
    }
}
