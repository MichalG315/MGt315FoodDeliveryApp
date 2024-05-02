package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends AddressMapper{
    @Mapping(source = "addressExtended", target = "address", qualifiedByName = "addressToString")
    RestaurantDTO map(Restaurant restaurant);
}
