package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.business.dao.MenuItemDAO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private MenuItemDAO menuItemDAO;

    @InjectMocks
    private MenuItemService menuItemService;

    @Test
    void findAvailableMenuItemsByRestaurantName() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);

        Mockito.when(menuItemDAO.findAvailableMenuItemsByRestaurantName(restaurantName))
                .thenReturn(List.of(menuItem));

        // when
        List<MenuItem> result = menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(menuItem));
    }

    @Test
    void findMenuItemByMenuItemNumber() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItem.getMenuItemNumber();

        Mockito.when(menuItemDAO.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(Optional.of(menuItem));

        // when
        MenuItem result = menuItemService.findMenuItemByMenuItemNumber(menuItemNumber);

        // then
        Assertions.assertThat(result).isEqualTo(menuItem);
    }

    @Test
    void findMenuItemByMenuItemNumberExceptionTest() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItem.getMenuItemNumber();

        Mockito.when(menuItemDAO.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(Optional.empty());

        // when, then
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        Assertions.assertThat("Could not find menu item with number: %s".formatted(menuItemNumber))
                .isEqualTo(exception.getMessage());
    }

    @Test
    void assignNumber() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();

        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);

        Mockito.when(menuItemDAO.findAvailableMenuItemsByRestaurantName(restaurantName))
                .thenReturn(List.of(menuItem));

        String expectedResult = restaurantName.substring(0, 3) + (List.of(menuItem).size() + 1);

        // when
        String result = menuItemService.assignNumber(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void saveNewMenuItem() {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Integer userId = user.getUserId();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItem.getMenuItemNumber();

        Mockito.when(userService.findUserId(userName))
                .thenReturn(userId);
        Mockito.when(restaurantService.findRestaurantNameByUserId(userId))
                .thenReturn(restaurantName);

        // when
        menuItemService.saveNewMenuItem(menuItem, userName);


    }
}