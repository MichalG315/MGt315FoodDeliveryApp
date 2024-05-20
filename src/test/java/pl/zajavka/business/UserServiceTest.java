package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.zajavka.business.dao.UserDAO;
import pl.zajavka.domain.*;
import pl.zajavka.util.DomainFixtures;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CustomerService customerService;
    @Mock
    private RoleService roleService;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private AddressService addressService;
    @Mock
    private AddressExtendedService addressExtendedService;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUser() {
        // given
        Role role = DomainFixtures.someRole1();
        User user = DomainFixtures.someUser2(role);
        Customer customer = DomainFixtures.someCustomer1();

        Mockito.when(roleService.findRoleByRoleId(user.getRole()))
                .thenReturn(role);
        Mockito.when(userDAO.findByUserName(user.getUserName()))
                .thenReturn(user);

        // when
        userService.saveUser(user, customer);

        // then
        Mockito.verify(customerService, Mockito.times(1))
                .saveCustomer(customer);
    }

    @Test
    void testSaveUser() {
        // given
        Role role = DomainFixtures.someRole1();
        User user = DomainFixtures.someUser2(role);
        Integer userId = user.getUserId();
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant4(addressExtended);

        Mockito.when(roleService.findRoleByRoleId(user.getRole()))
                .thenReturn(role);
        Mockito.when(userDAO.findByUserName(user.getUserName()))
                .thenReturn(user);
        Mockito.when(addressService.buildAddress(restaurant))
                .thenReturn(address);
        Mockito.when(addressExtendedService.buildExtenderAddress(restaurant))
                .thenReturn(addressExtended);

        // when
        userService.saveUser(user, restaurant);

        // then
        Mockito.verify(restaurantService, Mockito.times(1))
                .saveRestaurant(restaurant, address, addressExtended, userId);
    }

    @Test
    void findUserId() {
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Integer userId = user.getUserId();

        Mockito.when(userDAO.findByUserName(userName))
                .thenReturn(user);

        // when
        Integer result = userService.findUserId(user);

        // then
        Assertions.assertThat(result).isEqualTo(userId);
    }

    @Test
    void testFindUserId() {
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Integer userId = user.getUserId();

        Mockito.when(userDAO.findUserId(userName))
                .thenReturn(userId);

        // when
        Integer result = userService.findUserId(userName);

        // then
        Assertions.assertThat(result).isEqualTo(userId);
    }

    @Test
    void checkIfUserNameExists(){
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(userDAO.findByUserName(userName)).thenReturn(user);

        // when
        String result = userService.checkIfUserNameExists(userName);

        // then
        Assertions.assertThat(result).isEqualTo(userName);
    }

    @Test
    void checkIfUserNameExistsSecond(){
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(userDAO.findByUserName(userName)).thenReturn(null);

        // when
        String result = userService.checkIfUserNameExists(userName);

        // then
        Assertions.assertThat(result).isEqualTo(null);
    }

    @Test
    void checkIfEmailExists(){
        // given
        User user = DomainFixtures.someUser1();
        String email = user.getEmail();

        Mockito.when(userDAO.findByEmail(email)).thenReturn(user);

        // when
        String result = userService.checkIfEmailExists(email);

        // then
        Assertions.assertThat(result).isEqualTo(email);
    }

    @Test
    void checkIfEmailExistsSecond(){
        // given
        User user = DomainFixtures.someUser1();
        String email = user.getEmail();

        Mockito.when(userDAO.findByEmail(email)).thenReturn(null);

        // when
        String result = userService.checkIfEmailExists(email);

        // then
        Assertions.assertThat(result).isEqualTo(null);
    }
}