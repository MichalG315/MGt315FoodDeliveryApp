package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.UserDAO;
import pl.zajavka.domain.*;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final RestaurantService restaurantService;
    private final AddressService addressService;
    private final AddressExtendedService addressExtendedService;

    public void saveUser(User user, Customer customer) {
        Role role = findRole(user);
        userDAO.saveUser(encodePasswordAndSetRoleAndActive(user, role));
        Integer userId = findUserId(user);
        customerService.saveCustomer(customer.withUserId(userId).withEmail(user.getEmail()));
    }

    public void saveUser(User user, Restaurant restaurant) {
        Role role = findRole(user);
        userDAO.saveUser(encodePasswordAndSetRoleAndActive(user, role));
        Integer userId = findUserId(user);
        Address address = addressService.buildAddress(restaurant);
        AddressExtended addressExtended = addressExtendedService.buildExtenderAddress(restaurant);
        restaurantService.saveRestaurant(restaurant, address, addressExtended, userId);
    }

    public Integer findUserId(User user) {
        return userDAO.findByUserName(user.getUserName()).getUserId();
    }

    public Integer findUserId(String restaurantUserName) {
        return userDAO.findUserId(restaurantUserName);
    }

    private User encodePasswordAndSetRoleAndActive(User user, Role role) {
        return user.withPassword(passwordEncoder.encode(user.getPassword()))
                .withActive(true)
                .withRoles(Set.of(role));
    }

    private Role findRole(User user) {
        return roleService.findRoleByRoleId(user.getRole());
    }
}
