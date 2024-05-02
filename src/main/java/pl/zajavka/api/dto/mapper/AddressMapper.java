package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pl.zajavka.domain.AddressExtended;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Named("addressToString")
    default String addressToString(AddressExtended addressExtended){
        return String.format("%s, %s %s, %s %s",
                addressExtended.getAddress().getCountry(),
                addressExtended.getAddress().getCity(),
                addressExtended.getAddress().getPostalCode(),
                addressExtended.getAddress().getStreetName(),
                addressExtended.getStreetNumber()
        );
    }
}
