package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.configuration.PersistenceContainerTestConfiguration;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressExtendedJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;

import java.util.List;

import static pl.zajavka.util.EntityFixtures.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MenuItemRepositoryDataJpaTest {

    private final AddressJpaRepository addressJpaRepository;
    private final AddressExtendedJpaRepository addressExtendedJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final MenuItemJpaRepository menuItemJpaRepository;

    @Test
    void findAllMenuItemsByRestaurantNameWorkCorrectly() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        List<MenuItemEntity> menuItems = menuItemJpaRepository
                .saveAllAndFlush(List.of(someMenuItem1(restaurant), someMenuItem2(restaurant)));

        // when
        List<MenuItemEntity> result = menuItemJpaRepository
                .findAllByRestaurant_RestaurantName(restaurant.getRestaurantName());

        // then
        Assertions.assertThat(result).isEqualTo(menuItems);
    }

    @Test
    void findMenuItemByNumberWorkCorrectly() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        MenuItemEntity menuItem = menuItemJpaRepository.saveAndFlush(someMenuItem1(restaurant));

        // when
        MenuItemEntity result = menuItemJpaRepository
                .findByMenuItemNumber(menuItem.getMenuItemNumber()).orElseThrow();


        //then
        Assertions.assertThat(result).isEqualTo(menuItem);
    }

}