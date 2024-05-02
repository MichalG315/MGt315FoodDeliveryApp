package pl.zajavka.business.dao;

import pl.zajavka.domain.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    List<Restaurant> findAvailable();

}
