package pl.zajavka.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.Order;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.database.repository.util.DTOFixtures;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private FoodOrderService foodOrderService;
    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private OrderService orderService;

    @Test
    void buildAndSaveOrder() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Order order = DomainFixtures.someOrder1(user, restaurant);

        Mockito.when(menuItemMapper.mapFromDTO(menuItemDTO))
                .thenReturn(menuItem);

        ArgumentCaptor<Order> value1 = ArgumentCaptor.forClass(Order.class);
        ArgumentCaptor<String> value2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> value3 = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(foodOrderService)
                .saveFoodOrder(value1.capture(), value2.capture(), value3.capture());

        // when
        orderService.buildAndSaveOrder(restaurantName, userName, List.of(menuItemDTO));

        // then
        Assertions.assertEquals(restaurantName, value1.getValue().getRestaurantName());
        Assertions.assertEquals(userName, value1.getValue().getUserName());
        Assertions.assertEquals(menuItem.getPrice(), value1.getValue().getTotalAmount());

    }
}