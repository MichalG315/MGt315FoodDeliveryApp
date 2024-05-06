package pl.zajavka.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface FoodOrderJpaRepository extends JpaRepository<FoodOrderEntity, Integer> {

    List<FoodOrderEntity> findAllByCustomer(CustomerEntity customer);


    FoodOrderEntity findByFoodOrderNumber(String orderNumber);

    List<FoodOrderEntity> findAllByRestaurant(RestaurantEntity restaurant);
}
