package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;

import java.util.List;

@Repository
public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "restaurant"
            }
    )
    List<MenuItemEntity> findAllByRestaurant_RestaurantName(String restaurant);
}
