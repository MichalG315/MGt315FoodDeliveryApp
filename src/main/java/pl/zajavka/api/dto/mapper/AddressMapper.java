package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.RestaurantDeliveryAddress;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    default AddressDTO mapToDTO(RestaurantDeliveryAddress restaurantDeliveryAddress) {
        return AddressDTO.builder()
                .country(restaurantDeliveryAddress.getAddress().getCountry())
                .city(restaurantDeliveryAddress.getAddress().getCity())
                .postalCode(restaurantDeliveryAddress.getAddress().getPostalCode())
                .streetName(restaurantDeliveryAddress.getAddress().getStreetName())
                .build();


    }

    Address mapFromDTO(AddressDTO addressDTO);
}
