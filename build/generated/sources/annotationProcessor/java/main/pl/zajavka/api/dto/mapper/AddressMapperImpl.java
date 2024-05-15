package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.domain.Address;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-15T21:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address mapFromDTO(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.country( addressDTO.getCountry() );
        address.city( addressDTO.getCity() );
        address.postalCode( addressDTO.getPostalCode() );
        address.streetName( addressDTO.getStreetName() );

        return address.build();
    }
}
