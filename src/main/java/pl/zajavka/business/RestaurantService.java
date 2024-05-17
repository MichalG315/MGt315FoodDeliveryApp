package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.RestaurantDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.exception.NotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    public void saveRestaurant(Restaurant restaurant, Address address, AddressExtended addressExtended, Integer userId) {
        restaurantDAO.saveRestaurant(restaurant, address, addressExtended, userId);
    }

    public Page<Restaurant> findAvailable(int pageNo, int pageSize, String sortField, String sortDirection, String streetName, String city) {
        Pageable pageable = pageAndSort(pageNo, pageSize, sortField, sortDirection);
        boolean isCityHere = city.equals("All");
        boolean isStreetNameHere = streetName.equals("All");

        if (isStreetNameHere && isCityHere) {
            return restaurantDAO.findAvailable(pageable);
        } else if (isStreetNameHere) {
            return restaurantDAO.findAvailableWithCity(pageable, city);
        } else if (isCityHere) {
            return restaurantDAO.findAvailableWithStreetName(pageable, streetName);
        } else {
            return restaurantDAO.findAvailableWithStreetNameAndCity(pageable, streetName, city);
        }
    }

    private static Pageable pageAndSort(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }

    public Restaurant findByRestaurantName(String restaurantName) {
        Optional<Restaurant> restaurant = restaurantDAO.findByRestaurantName(restaurantName);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant named: %s".formatted(restaurantName));
        }
        return restaurant.get();
    }

    public String findRestaurantNameByUserId(Integer userId) {
        return restaurantDAO.findRestaurantByUserId(userId).getRestaurantName();
    }
}
