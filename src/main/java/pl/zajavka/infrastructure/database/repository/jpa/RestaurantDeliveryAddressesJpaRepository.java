package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

@Repository
public interface RestaurantDeliveryAddressesJpaRepository extends JpaRepository<RestaurantDeliveryAddressEntity, Integer> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "address.city",
                    "address.streetName"
            }
    )
    List<RestaurantDeliveryAddressEntity> findAll();

    List<RestaurantDeliveryAddressEntity> findAllByRestaurant(RestaurantEntity restaurant);
}
