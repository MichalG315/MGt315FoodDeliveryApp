package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = RegisterController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RegisterControllerWebMvcTest {

    private final MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private CustomerMapper customerMapper;
    @MockBean
    private RestaurantMapper restaurantMapper;

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void registerCustomerPage() throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserCustomerDTO.buildDefault().asMap().forEach(parameters::add);

        // when, then
        mockMvc.perform(get(RegisterController.REGISTER_CUSTOMER).params(parameters))
                .andExpect(status().isOk())
                .andExpect(view().name("register_customer_page"));
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void successfulCustomerRegistration() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        Customer customer = DomainFixtures.someCustomer1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserCustomerDTO.buildDefault().asMap().forEach(parameters::add);

        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_CUSTOMER_DONE).params(parameters))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void successfulCustomerRegistrationSecond() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        Customer customer = DomainFixtures.someCustomer1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserCustomerDTO.buildDefault().asMap().forEach(parameters::add);

        Mockito.when(userService.checkIfUserNameExists(userCustomerDTO.getUserName())).thenReturn(userName);
        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_CUSTOMER_DONE).params(parameters))
                .andExpect(MockMvcResultMatchers.view().name("error_invalid_register.html"));
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void successfulCustomerRegistrationThird() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String email = user.getEmail();
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        Customer customer = DomainFixtures.someCustomer1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserCustomerDTO.buildDefault().asMap().forEach(parameters::add);

        Mockito.when(userService.checkIfEmailExists(userCustomerDTO.getEmail())).thenReturn(email);
        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_CUSTOMER_DONE).params(parameters))
                .andExpect(MockMvcResultMatchers.view().name("error_invalid_register.html"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void registerRestaurantPage() throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserRestaurantDTO.buildDefault().asMap().forEach(parameters::add);

        // when, then
        mockMvc.perform(get(RegisterController.REGISTER_RESTAURANT).params(parameters))
                .andExpect(status().isOk())
                .andExpect(view().name("register_restaurant_page"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void successfulRestaurantRegistration() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserRestaurantDTO.buildDefault().asMap().forEach(parameters::add);

        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_RESTAURANT_DONE).params(parameters))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void successfulRestaurantRegistrationSecond() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserRestaurantDTO.buildDefault().asMap().forEach(parameters::add);

        Mockito.when(userService.checkIfUserNameExists(userRestaurantDTO.getUserName())).thenReturn(userName);
        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_RESTAURANT_DONE).params(parameters))
                .andExpect(MockMvcResultMatchers.view().name("error_invalid_register.html"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void successfulRestaurantRegistrationThird() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String email = user.getEmail();
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserRestaurantDTO.buildDefault().asMap().forEach(parameters::add);

        Mockito.when(userService.checkIfEmailExists(userRestaurantDTO.getEmail())).thenReturn(email);
        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_RESTAURANT_DONE).params(parameters))
                .andExpect(MockMvcResultMatchers.view().name("error_invalid_register.html"));
    }
}