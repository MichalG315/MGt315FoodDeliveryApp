package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

import java.util.Optional;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "addressExtended.address",
                    "addressExtended.customerAddresses"
            }
    )
    Optional<RestaurantEntity> findByRestaurantName(String restaurantName);

    RestaurantEntity findByUserId(Integer userId);
}
