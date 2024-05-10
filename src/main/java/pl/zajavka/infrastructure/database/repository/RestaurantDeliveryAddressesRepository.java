package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.RestaurantDeliveryAddressesDAO;
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
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantDeliveryAddressesRepository implements RestaurantDeliveryAddressesDAO {

    private final RestaurantDeliveryAddressesJpaRepository restaurantDeliveryAddressesJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final RestaurantDeliveryAddressesEntityMapper restaurantDeliveryAddressesEntityMapper;

    @Override
    public List<RestaurantDeliveryAddress> findAvailable() {
        return restaurantDeliveryAddressesJpaRepository.findAll().stream()
                .map(restaurantDeliveryAddressesEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<RestaurantDeliveryAddress> findDeliveryAddressByRestaurantName(String restaurantName) {
        RestaurantEntity restaurant = restaurantJpaRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() ->
                        new NotFoundException("Could not find restaurant named: %s".formatted(restaurantName))
                );
        return restaurantDeliveryAddressesJpaRepository.findAllByRestaurant(restaurant).stream()
                .map(restaurantDeliveryAddressesEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<RestaurantDeliveryAddress> findDeliveryAddresses(String restaurantUserName) {
        Integer userId = userJpaRepository.findByUserName(restaurantUserName).getId();
        RestaurantEntity restaurant = restaurantJpaRepository.findByUserId(userId);
        return restaurantDeliveryAddressesJpaRepository.findAllByRestaurant(restaurant).stream()
                .map(restaurantDeliveryAddressesEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void saveNewDeliveryAddress(String restaurantUserName, Address address) {
        Integer userId = userJpaRepository.findByUserName(restaurantUserName).getId();
        RestaurantEntity restaurant = restaurantJpaRepository.findByUserId(userId);
        AddressEntity addressEntity = buildAddressEntity(address);
        RestaurantDeliveryAddressEntity deliveryAddressEntity = RestaurantDeliveryAddressEntity.builder()
                .address(addressEntity)
                .restaurant(restaurant)
                .build();
        addressJpaRepository.save(addressEntity);
        restaurantDeliveryAddressesJpaRepository.save(deliveryAddressEntity);
    }

    private static AddressEntity buildAddressEntity(Address address) {
        return AddressEntity.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .streetName(address.getStreetName())
                .build();
    }

}
