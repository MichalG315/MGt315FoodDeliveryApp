package pl.zajavka.infrastructure.database.repository.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-16T21:17:39+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RestaurantDeliveryAddressesEntityMapperImpl implements RestaurantDeliveryAddressesEntityMapper {

    @Override
    public RestaurantDeliveryAddress mapFromEntity(RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity) {
        if ( restaurantDeliveryAddressEntity == null ) {
            return null;
        }

        RestaurantDeliveryAddress.RestaurantDeliveryAddressBuilder restaurantDeliveryAddress = RestaurantDeliveryAddress.builder();

        restaurantDeliveryAddress.restaurantDeliveryAddressId( restaurantDeliveryAddressEntity.getRestaurantDeliveryAddressId() );
        restaurantDeliveryAddress.address( addressEntityToAddress( restaurantDeliveryAddressEntity.getAddress() ) );

        return restaurantDeliveryAddress.build();
    }

    protected Address addressEntityToAddress(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.addressId( addressEntity.getAddressId() );
        address.country( addressEntity.getCountry() );
        address.city( addressEntity.getCity() );
        address.postalCode( addressEntity.getPostalCode() );
        address.streetName( addressEntity.getStreetName() );

        return address.build();
    }
}
