package pl.zajavka.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;

import java.util.Optional;

public interface RestaurantDAO {
    Optional<Restaurant> findByRestaurantName(String restaurantName);


    void saveRestaurant(Restaurant restaurant, Address address, AddressExtended addressExtended, Integer userId);

    Restaurant findRestaurantByUserId(Integer userId);

    Page<Restaurant> findAvailable(Pageable pageable);

    Page<Restaurant> findAvailableWithStreetNameAndCity(Pageable pageable, String streetName, String city);

    Page<Restaurant> findAvailableWithCity(Pageable pageable, String city);

    Page<Restaurant> findAvailableWithStreetName(Pageable pageable, String streetName);
}
