package pl.zajavka.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.MenuItemEntityMapper;
import pl.zajavka.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static pl.zajavka.util.EntityFixtures.*;

@ExtendWith(MockitoExtension.class)
class MenuItemRepositoryMockitoTest {

    @Mock
    private MenuItemJpaRepository menuItemJpaRepository;
    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @Mock
    private MenuItemEntityMapper menuItemEntityMapper;

    @InjectMocks
    private MenuItemRepository menuItemRepository;

    @Test
    void findAvailableMenuItemsByRestaurantName() {
        // given
        RestaurantEntity restaurant = someRestaurant1(someAddressExtended1(someAddress1()));
        String restaurantName = restaurant.getRestaurantName();
        MenuItemEntity menuItem1 = someMenuItem1(restaurant);

        Restaurant domainRestaurant = DomainFixtures.someRestaurant1();
        MenuItem domainMenuItem1 = DomainFixtures.someMenuItem1(domainRestaurant);


        Mockito.when(menuItemJpaRepository.findAllByRestaurant_RestaurantName(restaurantName))
                .thenReturn(List.of(menuItem1));
        Mockito.when(menuItemEntityMapper.mapFromEntity(menuItem1))
                .thenReturn(domainMenuItem1);


        // when
        List<MenuItem> result = menuItemRepository.findAvailableMenuItemsByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(domainMenuItem1));

    }

    @Test
    void saveMenuItem() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);

        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        MenuItemEntity menuItemEntity = someMenuItem1(restaurantEntity);

        Mockito.when(menuItemEntityMapper.mapToEntity(menuItem))
                .thenReturn(menuItemEntity);
        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName))
                .thenReturn(Optional.ofNullable(restaurantEntity));

        // when
        menuItemRepository.saveMenuItem(menuItem, restaurantName);

        // then
        Mockito.verify(menuItemEntityMapper, Mockito.times(1)).mapToEntity(menuItem);
        Mockito.verify(restaurantJpaRepository, Mockito.times(1))
                .findByRestaurantName(restaurantName);
        Mockito.verify(menuItemJpaRepository, Mockito.times(1)).saveAndFlush(menuItemEntity);
    }

    @Test
    void deleteAll() {
        // given, when, then
        menuItemRepository.deleteAll();
        Mockito.verify(menuItemJpaRepository, Mockito.times(1)).deleteAll();
    }

    @Test
    void findMenuItemByMenuItemNumber() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItem.getMenuItemNumber();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        MenuItemEntity menuItemEntity = someMenuItem1(restaurantEntity);

        Mockito.when(menuItemJpaRepository.findByMenuItemNumber(menuItemNumber))
                .thenReturn(Optional.ofNullable(menuItemEntity));
        Mockito.when(menuItemEntityMapper.mapFromEntity(menuItemEntity))
                .thenReturn(menuItem);

        // when
        Optional<MenuItem> result = menuItemRepository.findMenuItemByMenuItemNumber(menuItemNumber);

        // then
        Assertions.assertThat(result).isEqualTo(Optional.of(menuItem));
    }

    @Test
    void saveMenuItemExceptionTest() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);

        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        MenuItemEntity menuItemEntity = someMenuItem1(restaurantEntity);

        Mockito.when(menuItemEntityMapper.mapToEntity(menuItem))
                .thenReturn(menuItemEntity);
        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName))
                .thenReturn(Optional.empty());

        // when, then
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> menuItemRepository.saveMenuItem(menuItem, restaurantName));
        Assertions.assertThat("Could not find restaurant named: %s".formatted(restaurantName))
                .isEqualTo(exception.getMessage());

    }
}