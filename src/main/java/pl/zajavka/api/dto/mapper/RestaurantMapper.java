package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends AddressMapper {


    Restaurant mapFromDTO(UserRestaurantDTO userRestaurantDTO);

    Restaurant mapFromDTO(RestaurantDTO RestaurantDTO);

    RestaurantDTO mapToDTO(Restaurant restaurant);

}
