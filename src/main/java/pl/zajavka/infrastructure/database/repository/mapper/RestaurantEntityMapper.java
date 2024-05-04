package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

//    @Mapping(target = "addressExtended.address.addressesExtended", ignore = true)
//    @Mapping(target = "addressExtended.address.restaurantDeliveryAddresses", ignore = true)
//    @Mapping(target = "restaurantDeliveryAddresses", ignore = true)
//    @Mapping(target = "menuItems", ignore = true)
//    @Mapping(target = "foodOrders", ignore = true)
    Restaurant mapFromEntity(RestaurantEntity restaurantEntity);

    RestaurantEntity mapToEntity(Restaurant restaurant);

}
