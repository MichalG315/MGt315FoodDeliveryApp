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
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressExtendedJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantDeliveryAddressesJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;

import java.util.List;

import static pl.zajavka.util.EntityFixtures.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantDeliveryAddressesRepositoryDataJpaTest {

    private final AddressJpaRepository addressJpaRepository;
    private final AddressExtendedJpaRepository addressExtendedJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDeliveryAddressesJpaRepository restaurantDeliveryAddressesJpaRepository;

    @Test
    void findAllWorksCorrectly() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        restaurantDeliveryAddressesJpaRepository.saveAndFlush(RestaurantDeliveryAddressEntity.builder()
                .address(address)
                .restaurant(restaurant)
                .build());

        // when
        List<RestaurantDeliveryAddressEntity> all = restaurantDeliveryAddressesJpaRepository.findAll();

        // then
        Assertions.assertThat(all).hasSize(14);
    }

    @Test
    void findAllByRestaurantWorksCorrectly() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        RestaurantDeliveryAddressEntity restaurantDeliveryAddress = restaurantDeliveryAddressesJpaRepository
                .saveAndFlush(RestaurantDeliveryAddressEntity.builder()
                        .address(address)
                        .restaurant(restaurant)
                        .build());

        // when
        List<RestaurantDeliveryAddressEntity> result = restaurantDeliveryAddressesJpaRepository
                .findAllByRestaurant(restaurant);

        // then
        Assertions.assertThat(result.get(0)).isEqualTo(restaurantDeliveryAddress);

    }

}