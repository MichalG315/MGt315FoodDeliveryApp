package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import static pl.zajavka.infrastructure.database.repository.support.EntityFixtures.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantRepositoryDataJpaTest {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final AddressExtendedJpaRepository addressExtendedJpaRepository;
    private final RestaurantDeliveryAddressesJpaRepository restaurantDeliveryAddressesJpaRepository;

    @Test
    void canFindRestaurantByRestaurantName() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));

        // when
        RestaurantEntity result = restaurantJpaRepository
                .findByRestaurantName(restaurant.getRestaurantName()).orElseThrow();

        // then
        Assertions.assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void canFindRestaurantByUserId() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));

        // when
        RestaurantEntity result = restaurantJpaRepository.findByUserId(restaurant.getUserId());

        // then
        Assertions.assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void canFindAllRestaurantsByStreetName() {
        // given
//        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
//        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
//                .saveAndFlush(someAddressExtended1(address));
//        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
//        RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity = restaurantDeliveryAddressesJpaRepository.saveAndFlush(RestaurantDeliveryAddressEntity.builder()
//                .address(address)
//                .restaurant(restaurant)
//                .build());
//        Sort sort = Sort.by("restaurantName").ascending();
//        Pageable pageable = PageRequest.of(1, 3, sort);
//
//        // when
//        Page<RestaurantEntity> result = restaurantJpaRepository
//                .findAllByRestaurantDeliveryAddresses_Address_StreetName(pageable, address.getStreetName());
//
//        // then
//        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }
}