package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    public void saveRestaurant(Restaurant restaurant, Address address, AddressExtended addressExtended, Integer userId) {
        restaurantDAO.saveRestaurant(restaurant, address, addressExtended, userId);
    }

    public List<Restaurant> findAvailable() {
        return restaurantDAO.findAvailable();
    }

    public Restaurant findByRestaurantName(String restaurantName) {
        Optional<Restaurant> restaurant = restaurantDAO.findByRestaurantName(restaurantName);
        if (restaurant.isEmpty()){
            throw new NotFoundException("Could not find restaurant named: %s".formatted(restaurantName));
        }
        return restaurant.get();
    }

    public String findRestaurantNameByUserId(Integer userId) {
        return restaurantDAO.findRestaurantByUserId(userId).getRestaurantName();
    }
}
