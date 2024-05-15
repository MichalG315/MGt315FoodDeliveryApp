package pl.zajavka.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressExtendedJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.AddressEntityMapper;
import pl.zajavka.infrastructure.database.repository.mapper.AddressExtendedEntityMapper;
import pl.zajavka.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import pl.zajavka.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static pl.zajavka.util.EntityFixtures.*;

@ExtendWith(MockitoExtension.class)
class RestaurantRepositoryMockitoTest {

    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @Mock
    private AddressJpaRepository addressJpaRepository;
    @Mock
    private AddressExtendedJpaRepository addressExtendedJpaRepository;
    @Mock
    private RestaurantEntityMapper restaurantEntityMapper;
    @Mock
    private AddressEntityMapper addressEntityMapper;
    @Mock
    private AddressExtendedEntityMapper addressExtendedEntityMapper;

    @InjectMocks
    private RestaurantRepository restaurantRepository;

    @Test
    void findAvailable() {
        // given
        Pageable pageable = somePageable1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        Restaurant restaurant = DomainFixtures.someRestaurant1();

        Mockito.when(restaurantJpaRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(List.of(restaurantEntity)));
        Mockito.when(restaurantEntityMapper.mapFromEntity(restaurantEntity))
                .thenReturn(restaurant);

        // when
        Page<Restaurant> result = restaurantRepository.findAvailable(pageable);

        // then
        Assertions.assertThat(result.getContent()).hasSize(1);
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);

    }

    @Test
    void findAvailableWithStreetName() {
        // given
        Pageable pageable = somePageable1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        Address address = DomainFixtures.someAddress1();
        Restaurant restaurant = DomainFixtures.someRestaurant2(address);
        String restaurantDeliveryStreetName = restaurant.getRestaurantDeliveryStreetNames()
                .stream().findFirst().orElseThrow();

        Mockito.when(restaurantJpaRepository.findAllByRestaurantDeliveryAddresses_Address_StreetName(
                pageable, restaurantDeliveryStreetName)).thenReturn(new PageImpl<>(List.of(restaurantEntity)));
        Mockito.when(restaurantEntityMapper.mapFromEntity(restaurantEntity))
                .thenReturn(restaurant);

        // when
        Page<Restaurant> result = restaurantRepository
                .findAvailableWithStreetName(pageable, restaurantDeliveryStreetName);

        // then
        Assertions.assertThat(result.getContent()).hasSize(1);
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findAvailableWithCity() {
        // given
        Pageable pageable = somePageable1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        Address address = DomainFixtures.someAddress1();
        String city = address.getCity();
        Restaurant restaurant = DomainFixtures.someRestaurant2(address);

        Mockito.when(restaurantJpaRepository.findAllByRestaurantDeliveryAddresses_Address_City(
                pageable, city)).thenReturn(new PageImpl<>(List.of(restaurantEntity)));
        Mockito.when(restaurantEntityMapper.mapFromEntity(restaurantEntity))
                .thenReturn(restaurant);

        // when
        Page<Restaurant> result = restaurantRepository
                .findAvailableWithCity(pageable, city);

        // then
        Assertions.assertThat(result.getContent()).hasSize(1);
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findAvailableWithStreetNameAndCity() {
        // given
        Pageable pageable = somePageable1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        Address address = DomainFixtures.someAddress1();
        String city = address.getCity();
        Restaurant restaurant = DomainFixtures.someRestaurant2(address);
        String restaurantDeliveryStreetName = restaurant.getRestaurantDeliveryStreetNames()
                .stream().findFirst().orElseThrow();

        Mockito.when(restaurantJpaRepository
                .findAllByRestaurantDeliveryAddresses_Address_StreetNameAndRestaurantDeliveryAddresses_Address_City(
                        pageable, restaurantDeliveryStreetName, city)).thenReturn(
                new PageImpl<>(List.of(restaurantEntity)));
        Mockito.when(restaurantEntityMapper.mapFromEntity(restaurantEntity))
                .thenReturn(restaurant);

        // when
        Page<Restaurant> result = restaurantRepository
                .findAvailableWithStreetNameAndCity(pageable, restaurantDeliveryStreetName, city);

        // then
        Assertions.assertThat(result.getContent()).hasSize(1);
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findByRestaurantName() {
        // given
        Address address = DomainFixtures.someAddress1();
        Restaurant restaurant = DomainFixtures.someRestaurant2(address);
        String restaurantName = restaurant.getRestaurantName();
        Optional<RestaurantEntity> restaurantEntity = someRestaurant2(someAddressExtended1(someAddress1()));

        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName))
                .thenReturn(restaurantEntity);
        Mockito.when(restaurantEntityMapper.mapFromEntity(restaurantEntity.orElseThrow()))
                .thenReturn(restaurant);

        // when
        Optional<Restaurant> result = restaurantRepository.findByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result.orElseThrow()).isEqualTo(restaurant);
    }

    @Test
    void saveRestaurant() {
        // given
        Address address = DomainFixtures.someAddress1();
        Restaurant restaurant = DomainFixtures.someRestaurant2(address);
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        User user = DomainFixtures.someUser1();
        Integer userId = user.getUserId();

        AddressEntity addressEntity = someAddress1();
        AddressExtendedEntity addressExtendedEntity = someAddressExtended1(addressEntity);
        RestaurantEntity restaurantEntity = someRestaurant1(addressExtendedEntity);

        Mockito.when(addressEntityMapper.mapToEntity(address)).thenReturn(addressEntity);
        Mockito.when(addressExtendedEntityMapper.mapToEntity(addressExtended)).thenReturn(addressExtendedEntity);
        Mockito.when(addressJpaRepository.saveAndFlush(addressEntity)).thenReturn(addressEntity);
        Mockito.when(addressExtendedJpaRepository.saveAndFlush(addressExtendedEntity))
                .thenReturn(addressExtendedEntity);


        // when
        restaurantRepository.saveRestaurant(restaurant, address, addressExtended, userId);

        // then
        Mockito.verify(addressEntityMapper, Mockito.times(1)).mapToEntity(address);
        Mockito.verify(addressExtendedEntityMapper, Mockito.times(1)).mapToEntity(addressExtended);
        Mockito.verify(addressJpaRepository, Mockito.times(1)).saveAndFlush(addressEntity);
        Mockito.verify(addressExtendedJpaRepository, Mockito.times(1))
                .saveAndFlush(addressExtendedEntity);
        Mockito.verify(restaurantJpaRepository, Mockito.times(1)).saveAndFlush(restaurantEntity);
    }

    @Test
    void findRestaurantByUserId() {
        // given
        User user = DomainFixtures.someUser1();
        Integer userId = user.getUserId();
        Address address = DomainFixtures.someAddress1();
        Restaurant restaurant = DomainFixtures.someRestaurant2(address);

        AddressEntity addressEntity = someAddress1();
        AddressExtendedEntity addressExtendedEntity = someAddressExtended1(addressEntity);
        RestaurantEntity restaurantEntity = someRestaurant1(addressExtendedEntity);

        Mockito.when(restaurantJpaRepository.findByUserId(userId)).thenReturn(restaurantEntity);
        Mockito.when(restaurantEntityMapper.mapFromEntity(restaurantEntity))
                .thenReturn(restaurant);

        // when
        Restaurant result = restaurantRepository.findRestaurantByUserId(userId);

        // then
        Assertions.assertThat(result).isEqualTo(restaurant);
    }

    private static Pageable somePageable1() {
        Sort sort = Sort.by("restaurantName").ascending();
        return PageRequest.of(0, 3, sort);
    }

}