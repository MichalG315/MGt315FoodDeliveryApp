package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void saveRestaurant() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        User user = DomainFixtures.someUser1();
        Integer userId = user.getUserId();

        // when
        restaurantService.saveRestaurant(restaurant, address, addressExtended, userId);

        // then
        Mockito.verify(restaurantDAO, Mockito.times(1))
                .saveRestaurant(restaurant, address, addressExtended, userId);
    }

    @Test
    void findAvailable1() {
        // given
        Pageable pageable = somePageable1();
        int pageSize = pageable.getPageSize();
        String sortField = "restaurantName";
        String sortDir = "asc";
        Address address = DomainFixtures.someAddress1();
        String streetName = address.getStreetName();
        String city = address.getCity();
        Restaurant restaurant = DomainFixtures.someRestaurant1();

        Mockito.when(restaurantDAO.findAvailableWithStreetNameAndCity(pageable, streetName, city))
                .thenReturn(new PageImpl<>(List.of(restaurant)));

        // when
        Page<Restaurant> result = restaurantService.findAvailable(1, pageSize, sortField, sortDir, streetName, city);

        // then
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findAvailable2() {
        // given
        Pageable pageable = somePageable1();
        int pageSize = pageable.getPageSize();
        String sortField = "restaurantName";
        String sortDir = "asc";
        Address address = DomainFixtures.someAddress1();
        String streetName = address.getStreetName();
        String city = "All";
        Restaurant restaurant = DomainFixtures.someRestaurant1();

        Mockito.when(restaurantDAO.findAvailableWithStreetName(pageable, streetName))
                .thenReturn(new PageImpl<>(List.of(restaurant)));

        // when
        Page<Restaurant> result = restaurantService.findAvailable(1, pageSize, sortField, sortDir, streetName, city);

        // then
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findAvailable3() {
        // given
        Pageable pageable = somePageable1();
        int pageSize = pageable.getPageSize();
        String sortField = "restaurantName";
        String sortDir = "asc";
        Address address = DomainFixtures.someAddress1();
        String streetName = "All";
        String city = address.getCity();
        Restaurant restaurant = DomainFixtures.someRestaurant1();

        Mockito.when(restaurantDAO.findAvailableWithCity(pageable, city))
                .thenReturn(new PageImpl<>(List.of(restaurant)));

        // when
        Page<Restaurant> result = restaurantService.findAvailable(1, pageSize, sortField, sortDir, streetName, city);

        // then
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findAvailable4() {
        // given
        Pageable pageable = somePageable2();
        int pageSize = pageable.getPageSize();
        String sortField = "restaurantName";
        String sortDir = "desc";
        String streetName = "All";
        String city = "All";
        Restaurant restaurant = DomainFixtures.someRestaurant1();

        Mockito.when(restaurantDAO.findAvailable(pageable))
                .thenReturn(new PageImpl<>(List.of(restaurant)));

        // when
        Page<Restaurant> result = restaurantService.findAvailable(1, pageSize, sortField, sortDir, streetName, city);

        // then
        Assertions.assertThat(result.getContent().get(0)).isEqualTo(restaurant);
    }

    @Test
    void findByRestaurantName() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();

        Mockito.when(restaurantDAO.findByRestaurantName(restaurantName))
                .thenReturn(Optional.of(restaurant));

        // when
        Restaurant result = restaurantService.findByRestaurantName(restaurantName);

        // then
        Assertions.assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void findByRestaurantNameExceptionTest() {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();

        Mockito.when(restaurantDAO.findByRestaurantName(restaurantName))
                .thenReturn(Optional.empty());

        // when, then
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> restaurantService.findByRestaurantName(restaurantName));
        Assertions.assertThat("Could not find restaurant named: %s".formatted(restaurantName))
                .isEqualTo(exception.getMessage());
    }

    @Test
    void findRestaurantNameByUserId() {
        // given
        User user = DomainFixtures.someUser1();
        Integer userId = user.getUserId();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();

        Mockito.when(restaurantDAO.findRestaurantByUserId(userId))
                .thenReturn(restaurant);

        // when
        String result = restaurantService.findRestaurantNameByUserId(userId);

        // then
        Assertions.assertThat(result).isEqualTo(restaurantName);
    }

    private static Pageable somePageable1() {
        Sort sort = Sort.by("restaurantName").ascending();
        return PageRequest.of(0, 3, sort);
    }

    private static Pageable somePageable2() {
        Sort sort = Sort.by("restaurantName").descending();
        return PageRequest.of(0, 3, sort);
    }
}