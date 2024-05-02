package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.RestaurantEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public List<Restaurant> findAvailable() {
        List<Restaurant> list = restaurantJpaRepository.findAll().stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .toList();
        return list;
    }
}
