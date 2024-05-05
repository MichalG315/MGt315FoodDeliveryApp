package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-05T07:29:49+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public Restaurant mapFromDTO(UserRestaurantDTO userRestaurantDTO) {
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

    @Override
    public Restaurant mapFromDTO(RestaurantDTO RestaurantDTO) {
        if ( RestaurantDTO == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantName( RestaurantDTO.getRestaurantName() );
        restaurant.description( RestaurantDTO.getDescription() );
        restaurant.addressCountry( RestaurantDTO.getAddressCountry() );
        restaurant.addressCity( RestaurantDTO.getAddressCity() );
        restaurant.addressPostalCode( RestaurantDTO.getAddressPostalCode() );
        restaurant.addressStreetName( RestaurantDTO.getAddressStreetName() );
        restaurant.addressStreetNumber( RestaurantDTO.getAddressStreetNumber() );
        restaurant.completeAddress( RestaurantDTO.getCompleteAddress() );

        return restaurant.build();
    }

    @Override
    public RestaurantDTO mapToDTO(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDTO.RestaurantDTOBuilder restaurantDTO = RestaurantDTO.builder();

        restaurantDTO.restaurantName( restaurant.getRestaurantName() );
        restaurantDTO.description( restaurant.getDescription() );
        restaurantDTO.addressCountry( restaurant.getAddressCountry() );
        restaurantDTO.addressCity( restaurant.getAddressCity() );
        restaurantDTO.addressPostalCode( restaurant.getAddressPostalCode() );
        restaurantDTO.addressStreetName( restaurant.getAddressStreetName() );
        restaurantDTO.addressStreetNumber( restaurant.getAddressStreetNumber() );
        restaurantDTO.completeAddress( restaurant.getCompleteAddress() );

        return restaurantDTO.build();
    }
}
