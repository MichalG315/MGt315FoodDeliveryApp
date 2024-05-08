package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.RestaurantDeliveryAddressesDAO;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantDeliveryAddressesJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.RestaurantDeliveryAddressesEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantDeliveryAddressesRepository implements RestaurantDeliveryAddressesDAO {

    private final RestaurantDeliveryAddressesJpaRepository restaurantDeliveryAddressesJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
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

}
