package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {


    Restaurant mapFromDTO(UserRestaurantDTO userRestaurantDTO);

    RestaurantDTO mapToDTO(Restaurant restaurant);

}
