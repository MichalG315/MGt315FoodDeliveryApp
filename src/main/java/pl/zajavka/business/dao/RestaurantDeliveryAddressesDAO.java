package pl.zajavka.business.dao;

import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.RestaurantDeliveryAddress;

import java.util.List;

public interface RestaurantDeliveryAddressesDAO {
    List<RestaurantDeliveryAddress> findAvailable();

    List<RestaurantDeliveryAddress> findDeliveryAddressByRestaurantName(String restaurantName);

    List<RestaurantDeliveryAddress> findDeliveryAddresses(String restaurantUserName);

    void saveNewDeliveryAddress(String restaurantUserName, Address address);
}