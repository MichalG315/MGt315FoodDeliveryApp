package pl.zajavka.infrastructure.database.repository.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T18:13:49+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RestaurantEntityMapperImpl implements RestaurantEntityMapper {

    @Override
    public Restaurant mapFromEntity(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantName( restaurantEntity.getRestaurantName() );
        restaurant.description( restaurantEntity.getDescription() );
        restaurant.userId( restaurantEntity.getUserId() );

        return restaurant.build();
    }

    @Override
    public RestaurantEntity mapToEntity(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantEntity.RestaurantEntityBuilder restaurantEntity = RestaurantEntity.builder();

        restaurantEntity.restaurantName( restaurant.getRestaurantName() );
        restaurantEntity.description( restaurant.getDescription() );
        restaurantEntity.userId( restaurant.getUserId() );

        return restaurantEntity.build();
    }
}
