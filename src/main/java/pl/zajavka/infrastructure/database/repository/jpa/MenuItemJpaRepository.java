package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "restaurant"
            }
    )
    List<MenuItemEntity> findAllByRestaurant_RestaurantName(String restaurant);

    Optional<MenuItemEntity> findByMenuItemNumber(String menuItemNumber);
}
