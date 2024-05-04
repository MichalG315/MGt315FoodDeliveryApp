package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.UserDAO;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.Role;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.entity.UserEntity;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final RoleService roleService;

    public void saveUser(User user) {
        if (user.getRole().equals(1)) {

            Role role = roleService.findRoleByRoleId(user.getRole());
            UserEntity savedUser = userDAO.saveUser
                    (user.withPassword(passwordEncoder.encode(user.getPassword()))
                            .withActive(true)
                            .withRoles(Set.of(role)));
            Customer customer = customerService.buildCustomer(user, savedUser);
            customerService.saveCustomer(customer);
        } else {

        }
    }
}
