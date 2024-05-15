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
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemFoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.zajavka.util.DomainFixtures;

import java.util.Optional;

import static pl.zajavka.util.EntityFixtures.*;

@ExtendWith(MockitoExtension.class)
class MenuItemFoodOrderRepositoryMockitoTest {

    @Mock
    private MenuItemFoodOrderJpaRepository menuItemFoodOrderJpaRepository;
    @Mock
    private MenuItemJpaRepository menuItemJpaRepository;

    @InjectMocks
    private MenuItemFoodOrderRepository menuItemFoodOrderRepository;

    @Test
    void saveMenuItemFoodOrder() {
        // given
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), restaurant);
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1(someCustomer1(), restaurantEntity);
        String menuItemNumber = DomainFixtures.someMenuItem1(restaurant).getMenuItemNumber();

        Mockito.when(menuItemJpaRepository.findByMenuItemNumber(menuItemNumber))
                .thenReturn(Optional.ofNullable(someMenuItem3(restaurantEntity)));

        // when
        menuItemFoodOrderRepository.saveMenuItemFoodOrder(order, foodOrderEntity);

        // then
        Mockito.verify(menuItemJpaRepository, Mockito.times(1))
                .findByMenuItemNumber(menuItemNumber);
    }


    @Test
    void deleteAll() {
        // given, when, then
        menuItemFoodOrderRepository.deleteAll();
        Mockito.verify(menuItemFoodOrderJpaRepository, Mockito.times(1)).deleteAll();
    }

    @Test
    void saveMenuItemFoodOrderExceptionTest(){
        // given
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        Order order = DomainFixtures.someOrder1(DomainFixtures.someUser1(), DomainFixtures.someRestaurant1());
        FoodOrderEntity foodOrderEntity = someFoodOrderEntity1(someCustomer1(), restaurantEntity);
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String menuItemNumber = DomainFixtures.someMenuItem1(restaurant).getMenuItemNumber();
        Mockito.when(menuItemJpaRepository.findByMenuItemNumber(menuItemNumber))
                .thenReturn(Optional.empty());

        // when, then
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> menuItemFoodOrderRepository
                        .saveMenuItemFoodOrder(order, foodOrderEntity));
        Assertions.assertThat("Could not find menu item with number: %s".formatted(menuItemNumber))
                .isEqualTo(exception.getMessage());

    }
}