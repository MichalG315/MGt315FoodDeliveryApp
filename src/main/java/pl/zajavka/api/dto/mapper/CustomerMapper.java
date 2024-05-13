package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.domain.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {


    Customer map(UserCustomerDTO userCustomerDTO);

}
