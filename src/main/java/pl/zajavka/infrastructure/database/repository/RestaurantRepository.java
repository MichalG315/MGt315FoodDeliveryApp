package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressExtendedJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.AddressEntityMapper;
import pl.zajavka.infrastructure.database.repository.mapper.AddressExtendedEntityMapper;
import pl.zajavka.infrastructure.database.repository.mapper.RestaurantEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final AddressExtendedJpaRepository addressExtendedJpaRepository;


    private final RestaurantEntityMapper restaurantEntityMapper;
    private final AddressEntityMapper addressEntityMapper;
    private final AddressExtendedEntityMapper addressExtendedEntityMapper;

    @Override
    public Page<Restaurant> findAvailable(Pageable pageable) {
        return restaurantJpaRepository.findAll(pageable).map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public Page<Restaurant> findAvailableWithStreetName(Pageable pageable, String streetName) {
        return restaurantJpaRepository.findAllByRestaurantDeliveryAddresses_Address_StreetName(pageable, streetName)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public Page<Restaurant> findAvailableWithCity(Pageable pageable, String city) {
        return restaurantJpaRepository.findAllByRestaurantDeliveryAddresses_Address_City(pageable, city)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public Page<Restaurant> findAvailableWithStreetNameAndCity(Pageable pageable, String streetName, String city) {
        return restaurantJpaRepository.findAllByRestaurantDeliveryAddresses_Address_StreetNameAndRestaurantDeliveryAddresses_Address_City(pageable, streetName, city)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Restaurant> findByRestaurantName(String restaurantName) {
        return restaurantJpaRepository.findByRestaurantName(restaurantName)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant, Address address, AddressExtended addressExtended, Integer userId) {
        AddressEntity addressToSave = addressEntityMapper.mapToEntity(address);
        AddressExtendedEntity addressExtendedToSave = addressExtendedEntityMapper.mapToEntity(addressExtended);

        AddressEntity addressSaved = addressJpaRepository.saveAndFlush(addressToSave);
        addressExtendedToSave.setAddress(addressSaved);
        AddressExtendedEntity addressExtendedSaved = addressExtendedJpaRepository.saveAndFlush(addressExtendedToSave);
        RestaurantEntity restaurantToSave = RestaurantEntity.builder()
                .restaurantName(restaurant.getRestaurantName())
                .description(restaurant.getDescription())
                .userId(userId)
                .addressExtended(addressExtendedSaved)
                .build();
        restaurantJpaRepository.saveAndFlush(restaurantToSave);
    }

    @Override
    public Restaurant findRestaurantByUserId(Integer userId) {
        return restaurantEntityMapper.mapFromEntity(restaurantJpaRepository.findByUserId(userId));
    }
}