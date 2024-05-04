package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.domain.Restaurant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T12:40:01+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDTO map(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDTO.RestaurantDTOBuilder restaurantDTO = RestaurantDTO.builder();

        restaurantDTO.address( addressToString( restaurant.getAddressExtended() ) );
        restaurantDTO.restaurantName( restaurant.getRestaurantName() );
        restaurantDTO.description( restaurant.getDescription() );

        return restaurantDTO.build();
    }
}
