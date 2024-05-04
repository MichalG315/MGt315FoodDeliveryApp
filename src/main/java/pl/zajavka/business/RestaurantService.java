package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    public void saveRestaurant(Restaurant restaurant, Address address, AddressExtended addressExtended, Integer userId) {
        restaurantDAO.saveRestaurant(restaurant, address, addressExtended, userId);
    }
}
