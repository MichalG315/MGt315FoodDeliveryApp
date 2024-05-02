package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.domain.Restaurant;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final RestaurantService restaurantService;

    public List<Restaurant> availableRestaurants() {
        return restaurantService.findAvailable();
    }
}
