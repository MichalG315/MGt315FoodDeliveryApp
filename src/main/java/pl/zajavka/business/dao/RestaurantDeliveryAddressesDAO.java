package pl.zajavka.business.dao;

import pl.zajavka.domain.RestaurantDeliveryAddress;

import java.util.List;

public interface RestaurantDeliveryAddressesDAO {
    List<RestaurantDeliveryAddress> findAvailable();

    List<RestaurantDeliveryAddress> findDeliveryAddressByRestaurantName(String restaurantName);
}