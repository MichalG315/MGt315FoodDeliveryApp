package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantDeliveryAddressesEntityMapper {

    @Mapping(target = "address.addressesExtended", ignore = true)
    @Mapping(target = "address.restaurantDeliveryAddresses", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    RestaurantDeliveryAddress mapFromEntity(RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity);
}
