package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T18:13:49+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public Restaurant map(UserRestaurantDTO userRestaurantDTO) {
        if ( userRestaurantDTO == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantName( userRestaurantDTO.getRestaurantName() );
        restaurant.description( userRestaurantDTO.getDescription() );
        restaurant.addressCountry( userRestaurantDTO.getAddressCountry() );
        restaurant.addressCity( userRestaurantDTO.getAddressCity() );
        restaurant.addressPostalCode( userRestaurantDTO.getAddressPostalCode() );
        restaurant.addressStreetName( userRestaurantDTO.getAddressStreetName() );
        restaurant.addressStreetNumber( userRestaurantDTO.getAddressStreetNumber() );

        return restaurant.build();
    }
}
