package pl.zajavka.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Order;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.FoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemFoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.FoodOrderEntityMapper;
import pl.zajavka.util.DomainFixtures;
import pl.zajavka.infrastructure.security.entity.UserEntity;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pl.zajavka.util.EntityFixtures.*;

@ExtendWith(MockitoExtension.class)
class FoodOrderRepositoryMockitoTest {

    @Mock
    private FoodOrderJpaRepository foodOrderJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private CustomerJpaRepository customerJpaRepository;
    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @Mock
    private MenuItemFoodOrderRepository menuItemFoodOrderRepository;
    @Mock
    private MenuItemFoodOrderJpaRepository menuItemFoodOrderJpaRepository;
    @Mock
    private FoodOrderEntityMapper foodOrderEntityMapper;

    @InjectMocks
    private FoodOrderRepository foodOrderRepository;


    @Test
    void saveFoodOrder() {
        // given
        RestaurantEntity restaurant = someRestaurant1(someAddressExtended1(someAddress1()));
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), DomainFixtures.someRestaurant1());
        String userName = someUser1().getUserName();
        String restaurantName = restaurant.getRestaurantName();
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1
                (someCustomer1(), restaurant);

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(someUser1());
        Mockito.when(customerJpaRepository.findByEmail(someCustomer1().getEmail()))
                .thenReturn(someCustomer2());
        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName))
                .thenReturn(someRestaurant2(someAddressExtended1(someAddress1())));
        Mockito.when(foodOrderEntityMapper.mapToEntity(
                        order, someCustomer1(), restaurant))
                .thenReturn(foodOrderEntity);
        Mockito.when(foodOrderJpaRepository.saveAndFlush(foodOrderEntity))
                .thenReturn(foodOrderEntity);

        // when
        foodOrderRepository.saveFoodOrder(order, restaurantName, userName);

        // then
        Mockito.verify(userJpaRepository, Mockito.times(1)).findByUserName(userName);
        Mockito.verify(customerJpaRepository, Mockito.times(1)).findByEmail(someCustomer1().getEmail());
        Mockito.verify(restaurantJpaRepository, Mockito.times(1)).findByRestaurantName(restaurantName);
        Mockito.verify(foodOrderEntityMapper, Mockito.times(1)).mapToEntity(order, someCustomer1(), restaurant);
        Mockito.verify(foodOrderJpaRepository, Mockito.times(1)).saveAndFlush(foodOrderEntity);
    }

    @Test
    void deleteAll() {
        // given, when, then
        foodOrderRepository.deleteAll();
        Mockito.verify(foodOrderJpaRepository, Mockito.times(1)).deleteAll();

    }

    @Test
    void availableFoodOrdersByUserName() {
        // given
        String userName = someUser1().getUserName();
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1
                (someCustomer1(), someRestaurant1(someAddressExtended1(someAddress1())));
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), DomainFixtures.someRestaurant1());

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(someUser1());
        Mockito.when(customerJpaRepository.findByEmail(someCustomer1().getEmail()))
                .thenReturn(someCustomer2());
        Mockito.when(foodOrderJpaRepository.findAllByCustomer(someCustomer1()))
                .thenReturn(List.of(foodOrderEntity));
        Mockito.when(foodOrderEntityMapper.mapFromEntity(foodOrderEntity, userName))
                .thenReturn(order);

        // when
        List<Order> result = foodOrderRepository.availableFoodOrdersByUserName(userName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(order));
    }

    @Test
    void availableFoodOrdersByRestaurantName() {
        // given
        RestaurantEntity restaurant = someRestaurant1(someAddressExtended1(someAddress1()));
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), DomainFixtures.someRestaurant1());
        String userName = someUser1().getUserName();
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1
                (someCustomer1(), restaurant);

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(someUser1());
        Mockito.when(restaurantJpaRepository.findByUserId(restaurant.getUserId())).thenReturn(restaurant);
        Mockito.when(foodOrderJpaRepository.findAllByRestaurant(restaurant))
                .thenReturn(List.of(foodOrderEntity));
        Mockito.when(foodOrderEntityMapper.mapFromEntity(foodOrderEntity))
                .thenReturn(order);

        // when
        List<Order> result = foodOrderRepository.availableFoodOrdersByRestaurantName(userName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(order));
    }

    @Test
    void deleteOrder() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        User user = DomainFixtures.someUser1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1
                (someCustomer1(), someRestaurant1(someAddressExtended1(someAddress1())));
        MenuItemFoodOrderEntity menuItemFoodOrderEntity = someMenuItemFoodOrder1(someMenuItem1(restaurantEntity), foodOrderEntity);
        foodOrderEntity.setMenuItemFoodOrders(Set.of(menuItemFoodOrderEntity));
        List<MenuItemFoodOrderEntity> menuItemFoodOrderEntities = foodOrderEntity.getMenuItemFoodOrders().stream().toList();
        Mockito.when(foodOrderJpaRepository.findByFoodOrderNumber(orderNumber))
                .thenReturn(foodOrderEntity);

        // when
        foodOrderRepository.deleteOrder(orderNumber);

        // then
        Mockito.verify(menuItemFoodOrderJpaRepository, Mockito.times(1)).deleteAll(menuItemFoodOrderEntities);
        Mockito.verify(foodOrderJpaRepository, Mockito.times(1)).delete(foodOrderEntity);
    }

    @Test
    void findFoodOrder() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        User user = DomainFixtures.someUser1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1(someCustomer1(), restaurantEntity);
        String orderNumber = order.getOrderNumber();

        Mockito.when(foodOrderJpaRepository.findByFoodOrderNumber(orderNumber)).thenReturn(foodOrderEntity);
        Mockito.when(foodOrderEntityMapper.mapFromEntity(foodOrderEntity)).thenReturn(order);

        // when
        Order result = foodOrderRepository.findFoodOrder(orderNumber);

        // then
        Assertions.assertThat(result).isEqualTo(order);
    }

    @Test
    void setCompletedDateTime() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        User user = DomainFixtures.someUser1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1(someCustomer1(), restaurantEntity);
        String orderNumber = order.getOrderNumber();
        OffsetDateTime completedDateTime = OffsetDateTime
                .of(2020, 10, 10, 10, 10, 10, 10, ZoneOffset.UTC);

        Mockito.when(foodOrderJpaRepository.findByFoodOrderNumber(orderNumber)).thenReturn(foodOrderEntity);

        // when
        foodOrderRepository.setCompletedDateTime(orderNumber, completedDateTime);

        //then
        Assertions.assertThat(foodOrderEntity.getCompletedDateTime()).isEqualTo(completedDateTime);
        Assertions.assertThat(foodOrderEntity.getStatus()).isEqualTo("Delivered");
    }

    @Test
    void saveFoodOrderException1Test() {
        // given
        RestaurantEntity restaurant = someRestaurant1(someAddressExtended1(someAddress1()));
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), DomainFixtures.someRestaurant1());
        String userName = someUser1().getUserName();
        String restaurantName = restaurant.getRestaurantName();
        String email = someUser1().getEmail();

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(someUser1());
        Mockito.when(customerJpaRepository.findByEmail(email)).thenReturn(Optional.empty());

        NotFoundException exception1 = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> foodOrderRepository.saveFoodOrder(order, restaurantName, userName));
        Assertions.assertThat("Could not find customer with email: %s".formatted(email)).isEqualTo(exception1.getMessage());
    }

    @Test
    void saveFoodOrderException2Test() {
        // given
        RestaurantEntity restaurant = someRestaurant1(someAddressExtended1(someAddress1()));
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), DomainFixtures.someRestaurant1());
        String userName = someUser1().getUserName();
        String restaurantName = restaurant.getRestaurantName();

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(someUser1());
        Mockito.when(customerJpaRepository.findByEmail(someCustomer1().getEmail()))
                .thenReturn(someCustomer2());
        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName)).thenReturn(Optional.empty());

        NotFoundException exception1 = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> foodOrderRepository.saveFoodOrder(order, restaurantName, userName));
        Assertions.assertThat("Could not find restaurant named: %s".formatted(restaurantName)).isEqualTo(exception1.getMessage());
    }

    @Test
    void availableFoodOrdersByUserNameExceptionTest() {
        // given
        UserEntity user = someUser1();
        String userName = user.getUserName();
        String customerEmail = user.getEmail();

        Mockito.when(userJpaRepository.findByUserName(userName)).thenReturn(user);

        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> foodOrderRepository.availableFoodOrdersByUserName(userName));
        Assertions.assertThat("Could not find customer with email: %s".formatted(customerEmail)).isEqualTo(exception.getMessage());
    }
}