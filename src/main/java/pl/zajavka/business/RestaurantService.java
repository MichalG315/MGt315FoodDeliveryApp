package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    public List<Restaurant> findAvailable() {
        return restaurantDAO.findAvailable();
    }

    public Restaurant findByRestaurantName(String restaurantName) {
        Optional<Restaurant> restaurant = restaurantDAO.findByRestaurantName(restaurantName);
        if (restaurant.isEmpty()){
            throw new NotFoundException("Could not find restaurant by name: %s".formatted(restaurantName));
        }
        return restaurant.get();
    }
}
