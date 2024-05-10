package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    Restaurant mapFromDTO(UserRestaurantDTO userRestaurantDTO);

    RestaurantDTO mapToDTO(Restaurant restaurant);

}
