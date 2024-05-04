package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.Restaurant;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    Customer map(UserCustomerDTO userCustomerDTO);

}
