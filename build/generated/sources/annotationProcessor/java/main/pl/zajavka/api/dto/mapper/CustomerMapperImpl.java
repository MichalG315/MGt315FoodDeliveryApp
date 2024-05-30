package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.domain.Customer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T11:08:20+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer map(UserCustomerDTO userCustomerDTO) {
        if ( userCustomerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.name( userCustomerDTO.getName() );
        customer.surname( userCustomerDTO.getSurname() );
        customer.email( userCustomerDTO.getEmail() );
        customer.phone( userCustomerDTO.getPhone() );

        return customer.build();
    }
}
