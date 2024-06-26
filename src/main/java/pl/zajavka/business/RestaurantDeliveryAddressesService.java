package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.dao.RestaurantDeliveryAddressesDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.RestaurantDeliveryAddress;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantDeliveryAddressesService {

    private final RestaurantDeliveryAddressesDAO restaurantDeliveryAddressesDAO;

    @Transactional
    public Set<String> findAvailableCities() {
        TreeSet<String> treeSet = restaurantDeliveryAddressesDAO.findAvailable().stream()
                .map(RestaurantDeliveryAddress::getAddress)
                .map(Address::getCity)
                .collect(Collectors.toCollection(TreeSet::new));
        treeSet.add("All");
        return treeSet;
    }

    @Transactional
    public Set<String> findAvailableStreetNames() {
        TreeSet<String> treeSet = restaurantDeliveryAddressesDAO.findAvailable().stream()
                .map(RestaurantDeliveryAddress::getAddress)
                .map(Address::getStreetName)
                .collect(Collectors.toCollection(TreeSet::new));
        treeSet.add("All");
        return treeSet;
    }

    @Transactional
    public Set<String> findStreetNamesByRestaurantName(String restaurantName) {
        return restaurantDeliveryAddressesDAO.findDeliveryAddressByRestaurantName(restaurantName).stream()
                .map(RestaurantDeliveryAddress::getAddress)
                .map(Address::getStreetName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Transactional
    public Set<String> findCitesByRestaurantName(String restaurantName) {
        return restaurantDeliveryAddressesDAO.findDeliveryAddressByRestaurantName(restaurantName).stream()
                .map(RestaurantDeliveryAddress::getAddress)
                .map(Address::getCity)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Transactional
    public List<RestaurantDeliveryAddress> findDeliveryAddresses(String restaurantUserName) {
        return restaurantDeliveryAddressesDAO.findDeliveryAddresses(restaurantUserName);
    }

    @Transactional
    public void saveNewDeliveryAddress(String restaurantUserName, Address address) {
        restaurantDeliveryAddressesDAO.saveNewDeliveryAddress(restaurantUserName, address);
    }
}
