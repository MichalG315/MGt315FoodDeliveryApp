package pl.zajavka.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantDeliveryAddressesJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.RestaurantDeliveryAddressesEntityMapper;
import pl.zajavka.infrastructure.security.entity.UserEntity;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;
import pl.zajavka.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static pl.zajavka.util.EntityFixtures.*;

@ExtendWith(MockitoExtension.class)
class RestaurantDeliveryAddressesRepositoryMockitoTest {

    @Mock
    private RestaurantDeliveryAddressesJpaRepository restaurantDeliveryAddressesJpaRepository;
    @Mock
    private AddressJpaRepository addressJpaRepository;
    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;
    @Mock
    private RestaurantDeliveryAddressesEntityMapper restaurantDeliveryAddressesEntityMapper;

    @InjectMocks
    private RestaurantDeliveryAddressesRepository restaurantDeliveryAddressesRepository;

    @Test
    void findAvailable() {
        // given
        RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity = someRestaurantDeliveryAddress1(
                someAddress1(), someRestaurant1(someAddressExtended1(someAddress1())));

        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures.someRestaurantDeliveryAddress(
                DomainFixtures.someAddress1(), DomainFixtures.someRestaurant1());

        Mockito.when(restaurantDeliveryAddressesJpaRepository.findAll())
                .thenReturn(List.of(restaurantDeliveryAddressEntity));
        Mockito.when(restaurantDeliveryAddressesEntityMapper.mapFromEntity(restaurantDeliveryAddressEntity))
                .thenReturn(restaurantDeliveryAddress);

        // when
        List<RestaurantDeliveryAddress> result = restaurantDeliveryAddressesRepository.findAvailable();

        // then
        Assertions.assertThat(result).isEqualTo(List.of(restaurantDeliveryAddress));
    }

    @Test
    void findDeliveryAddressByRestaurantName() {
        // given
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        String restaurantName = restaurantEntity.getRestaurantName();
        RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity = someRestaurantDeliveryAddress1(
                someAddress1(), someRestaurant1(someAddressExtended1(someAddress1())));
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures.someRestaurantDeliveryAddress(
                DomainFixtures.someAddress1(), DomainFixtures.someRestaurant1());

        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName))
                .thenReturn(Optional.of(restaurantEntity));
        Mockito.when(restaurantDeliveryAddressesJpaRepository.findAllByRestaurant(restaurantEntity))
                .thenReturn(List.of(restaurantDeliveryAddressEntity));
        Mockito.when(restaurantDeliveryAddressesEntityMapper.mapFromEntity(restaurantDeliveryAddressEntity))
                .thenReturn(restaurantDeliveryAddress);

        // when
        List<RestaurantDeliveryAddress> result = restaurantDeliveryAddressesRepository
                .findDeliveryAddressByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(restaurantDeliveryAddress));
    }

    @Test
    void findDeliveryAddresses() {
        // given
        UserEntity user = someUser1();
        String userName = user.getUserName();
        int id = user.getId();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity = someRestaurantDeliveryAddress1(
                someAddress1(), someRestaurant1(someAddressExtended1(someAddress1())));
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures.someRestaurantDeliveryAddress(
                DomainFixtures.someAddress1(), DomainFixtures.someRestaurant1());

        Mockito.when(userJpaRepository.findByUserName(userName))
                .thenReturn(user);
        Mockito.when(restaurantJpaRepository.findByUserId(id))
                .thenReturn(restaurantEntity);
        Mockito.when(restaurantDeliveryAddressesJpaRepository.findAllByRestaurant(restaurantEntity))
                .thenReturn(List.of(restaurantDeliveryAddressEntity));
        Mockito.when(restaurantDeliveryAddressesEntityMapper.mapFromEntity(restaurantDeliveryAddressEntity))
                .thenReturn(restaurantDeliveryAddress);

        // when
        List<RestaurantDeliveryAddress> result = restaurantDeliveryAddressesRepository.findDeliveryAddresses(userName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(restaurantDeliveryAddress));
    }

    @Test
    void saveNewDeliveryAddress() {
        // given
        Address address = DomainFixtures.someAddress1();
        UserEntity user = someUser1();
        String userName = user.getUserName();
        int id = user.getId();
        AddressEntity addressEntity = someAddress1();
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(addressEntity));
        RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity = someRestaurantDeliveryAddress1(
                someAddress1(), someRestaurant1(someAddressExtended1(someAddress1())));

        Mockito.when(userJpaRepository.findByUserName(userName)).
                thenReturn(user);
        Mockito.when(restaurantJpaRepository.findByUserId(id))
                .thenReturn(restaurantEntity);

        // when
        restaurantDeliveryAddressesRepository.saveNewDeliveryAddress(userName, address);

        // then
        Mockito.verify(userJpaRepository, Mockito.times(1)).findByUserName(userName);
        Mockito.verify(restaurantJpaRepository, Mockito.times(1)).findByUserId(id);
        Mockito.verify(addressJpaRepository, Mockito.times(1)).saveAndFlush(addressEntity);
        Mockito.verify(restaurantDeliveryAddressesJpaRepository, Mockito.times(1))
                .saveAndFlush(restaurantDeliveryAddressEntity);
    }

    @Test
    void findDeliveryAddressByRestaurantNameExceptionTest(){
        // given
        RestaurantEntity restaurantEntity = someRestaurant1(someAddressExtended1(someAddress1()));
        String restaurantName = restaurantEntity.getRestaurantName();
        RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity = someRestaurantDeliveryAddress1(
                someAddress1(), someRestaurant1(someAddressExtended1(someAddress1())));
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures.someRestaurantDeliveryAddress(
                DomainFixtures.someAddress1(), DomainFixtures.someRestaurant1());

        Mockito.when(restaurantJpaRepository.findByRestaurantName(restaurantName))
                .thenReturn(Optional.empty());

        // when, then
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> restaurantDeliveryAddressesRepository
                        .findDeliveryAddressByRestaurantName(restaurantName));
        Assertions.assertThat("Could not find restaurant named: %s".formatted(restaurantName))
                .isEqualTo(exception.getMessage());
    }
}