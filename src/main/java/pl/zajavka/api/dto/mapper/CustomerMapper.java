package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    Customer map(UserCustomerDTO userCustomerDTO);

}
