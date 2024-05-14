package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.business.dao.RestaurantDeliveryAddressesDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class RestaurantDeliveryAddressesServiceTest {

    @Mock
    private RestaurantDeliveryAddressesDAO restaurantDeliveryAddressesDAO;

    @InjectMocks
    private RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;

    @Test
    void findAvailableCities() {
        // given
        Address address = DomainFixtures.someAddress1();
        String city = address.getCity();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures
                .someRestaurantDeliveryAddress(address, restaurant);

        Mockito.when(restaurantDeliveryAddressesDAO.findAvailable())
                .thenReturn(List.of(restaurantDeliveryAddress));

        // when
        Set<String> result = restaurantDeliveryAddressesService.findAvailableCities();

        // then
        Assertions.assertThat(result).isEqualTo(Set.of(city, "All"));
    }

    @Test
    void findAvailableStreetNames() {
        // given
        Address address = DomainFixtures.someAddress1();
        String streetName = address.getStreetName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures
                .someRestaurantDeliveryAddress(address, restaurant);

        Mockito.when(restaurantDeliveryAddressesDAO.findAvailable())
                .thenReturn(List.of(restaurantDeliveryAddress));

        // when
        Set<String> result = restaurantDeliveryAddressesService.findAvailableStreetNames();

        // then
        Assertions.assertThat(result).isEqualTo(Set.of(streetName, "All"));
    }

    @Test
    void findStreetNamesByRestaurantName() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        Address address = DomainFixtures.someAddress1();
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures
                .someRestaurantDeliveryAddress(address, restaurant);
        Mockito.when(restaurantDeliveryAddressesDAO.findDeliveryAddressByRestaurantName(restaurantName))
                .thenReturn(List.of(restaurantDeliveryAddress));
        String streetName = address.getStreetName();

        // when
        Set<String> result = restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(Set.of(streetName));
    }

    @Test
    void findCitesByRestaurantName() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        Address address = DomainFixtures.someAddress1();
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures
                .someRestaurantDeliveryAddress(address, restaurant);
        Mockito.when(restaurantDeliveryAddressesDAO.findDeliveryAddressByRestaurantName(restaurantName))
                .thenReturn(List.of(restaurantDeliveryAddress));
        String streetName = address.getStreetName();

        // when
        Set<String> result = restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(Set.of(streetName));
    }

    @Test
    void findDeliveryAddresses() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Address address = DomainFixtures.someAddress1();
        RestaurantDeliveryAddress restaurantDeliveryAddress = DomainFixtures
                .someRestaurantDeliveryAddress(address, restaurant);
        Mockito.when(restaurantDeliveryAddressesDAO.findDeliveryAddresses(userName))
                .thenReturn(List.of(restaurantDeliveryAddress));

        // when
        List<RestaurantDeliveryAddress> result = restaurantDeliveryAddressesService
                .findDeliveryAddresses(userName);

        // then
        Assertions.assertThat(result).isEqualTo(List.of(restaurantDeliveryAddress));
    }

    @Test
    void saveNewDeliveryAddress() {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Address address = DomainFixtures.someAddress1();

        // when
        restaurantDeliveryAddressesService.saveNewDeliveryAddress(userName, address);

        // then
        Mockito.verify(restaurantDeliveryAddressesDAO, Mockito.times(1))
                .saveNewDeliveryAddress(userName, address);
    }
}