package pl.zajavka.business.dao;

import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {
    List<Restaurant> findAvailable();

    Optional<Restaurant> findByRestaurantName(String restaurantName);


    void saveRestaurant(Restaurant restaurant, Address address, AddressExtended addressExtended, Integer userId);
}
