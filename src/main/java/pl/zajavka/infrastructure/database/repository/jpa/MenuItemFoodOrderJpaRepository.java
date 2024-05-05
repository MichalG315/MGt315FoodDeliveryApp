package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;

import java.util.List;

@Repository
public interface MenuItemFoodOrderJpaRepository extends JpaRepository<MenuItemFoodOrderEntity, Integer> {

}
