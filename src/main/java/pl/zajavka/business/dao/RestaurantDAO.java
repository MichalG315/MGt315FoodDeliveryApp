package pl.zajavka.business.dao;

import pl.zajavka.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {
    List<Restaurant> findAvailable();

    Optional<Restaurant> findByRestaurantName(String restaurantName);
}
