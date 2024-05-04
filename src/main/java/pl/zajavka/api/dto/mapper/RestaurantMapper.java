package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends AddressMapper {


    Restaurant map(UserRestaurantDTO userRestaurantDTO);



}
