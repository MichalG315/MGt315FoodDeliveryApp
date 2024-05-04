package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User map(UserCustomerDTO userCustomerDTO);

    User map(UserRestaurantDTO userRestaurantDTO);
}
