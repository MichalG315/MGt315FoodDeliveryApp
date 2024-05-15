package pl.zajavka.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.api.dto.mapper.CustomerMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.api.dto.mapper.UserMapper;
import pl.zajavka.business.UserService;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

@ExtendWith(MockitoExtension.class)
class RegisterControllerMockitoTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserService userService;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RegisterController registerController;

    @Test
    void successfulCustomerRegistration() {
        // given
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        User user = DomainFixtures.someUser1();
        Customer customer = DomainFixtures.someCustomer1();
        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);

        // when
        registerController.successfulCustomerRegistration(userCustomerDTO);

        // then
        Mockito.verify(userService, Mockito.times(1))
                .saveUser(user, customer);
    }
    @Test
    void successfulRestaurantRegistration() {
        // given
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);

        // when
        registerController.successfulRestaurantRegistration(userRestaurantDTO);

        // then
        Mockito.verify(userService, Mockito.times(1))
                .saveUser(user, restaurant);
    }
}