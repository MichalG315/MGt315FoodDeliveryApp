package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;

@Repository
public interface FoodOrderJpaRepository extends JpaRepository<FoodOrderEntity, Integer> {
}
