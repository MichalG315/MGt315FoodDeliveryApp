package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.business.dao.FoodOrderDAO;
import pl.zajavka.domain.Order;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;

import java.time.OffsetDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FoodOrderServiceTest {

    @Mock
    private FoodOrderDAO foodOrderDAO;

    @InjectMocks
    private FoodOrderService foodOrderService;

    @Test
    void availableFoodOrdersByUserName() {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);

        Mockito.when(foodOrderDAO.availableFoodOrdersByUserName(userName))
                .thenReturn(List.of(order));

        // when
        List<Order> result = foodOrderService.availableFoodOrdersByUserName(userName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(order));
    }

    @Test
    void availableFoodOrdersByRestaurantName() {
        // given
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        Order order = DomainFixtures.someOrder1(user, restaurant);

        Mockito.when(foodOrderDAO.availableFoodOrdersByRestaurantName(restaurantName))
                .thenReturn(List.of(order));

        // when
        List<Order> result = foodOrderService.availableFoodOrdersByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(order));
    }

    @Test
    void saveFoodOrder() {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        Order order = DomainFixtures.someOrder1(user, restaurant);

        // when, then
        foodOrderService.saveFoodOrder(order, restaurantName, userName);

        Mockito.verify(foodOrderDAO, Mockito.times(1))
                .saveFoodOrder(order, restaurantName, userName);
    }

    @Test
    void deleteFoodOrder() {
        // given
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        // when, then
        foodOrderService.deleteFoodOrder(orderNumber);

        Mockito.verify(foodOrderDAO, Mockito.times(1))
                .deleteOrder(orderNumber);
    }

    @Test
    void findFoodOrderReceivedDateTime() {
        // given
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();
        OffsetDateTime receivedDateTime = order.getReceivedDateTime();
        Mockito.when(foodOrderDAO.findFoodOrder(orderNumber))
                .thenReturn(order);

        // when
        OffsetDateTime result = foodOrderService.findFoodOrderReceivedDateTime(orderNumber);

        // then
        Assertions.assertThat(result).isEqualTo(receivedDateTime);
    }

    @Test
    void setCompletedDateTime() {
        // given
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();
        OffsetDateTime receivedDateTime = OffsetDateTime.now();

        // when, then
        foodOrderService.setCompletedDateTime(orderNumber);

        Mockito.verify(foodOrderDAO, Mockito.times(1))
                .setCompletedDateTime(orderNumber, receivedDateTime);
    }
}