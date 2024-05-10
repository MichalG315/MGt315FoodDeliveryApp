package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

import java.util.TreeSet;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {


    default Restaurant mapFromEntity(RestaurantEntity restaurantEntity) {
        return Restaurant.builder()
                .restaurantName(restaurantEntity.getRestaurantName())
                .description(restaurantEntity.getDescription())
                .addressCountry(restaurantEntity.getAddressExtended().getAddress().getCountry())
                .addressCity(restaurantEntity.getAddressExtended().getAddress().getCity())
                .addressPostalCode(restaurantEntity.getAddressExtended().getAddress().getPostalCode())
                .addressStreetName(restaurantEntity.getAddressExtended().getAddress().getStreetName())
                .addressStreetNumber(restaurantEntity.getAddressExtended().getStreetNumber())
                .completeAddress(buildCompleteAddress(restaurantEntity))
                .restaurantDeliveryStreetNames(restaurantEntity.getRestaurantDeliveryAddresses().stream()
                        .map(a -> a.getAddress().getStreetName())
                        .collect(Collectors.toCollection(TreeSet::new)))
                .build();
    }

    RestaurantEntity mapToEntity(Restaurant restaurant);

    private String buildCompleteAddress(RestaurantEntity restaurantEntity) {
        return String.format("%s, %s %s, %s %s",
                restaurantEntity.getAddressExtended().getAddress().getCountry(),
                restaurantEntity.getAddressExtended().getAddress().getCity(),
                restaurantEntity.getAddressExtended().getAddress().getPostalCode(),
                restaurantEntity.getAddressExtended().getAddress().getStreetName(),
                restaurantEntity.getAddressExtended().getStreetNumber());
    }

}
