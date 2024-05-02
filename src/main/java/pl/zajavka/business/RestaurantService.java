package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Restaurant;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    public List<Restaurant> findAvailable() {
        return restaurantDAO.findAvailable();
    }
}
