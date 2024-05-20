package pl.zajavka.api.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = RegisterRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RegisterRestControllerWebMvcTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;

    @MockBean
    private CustomerMapper customerMapper;
    @MockBean
    private RestaurantMapper restaurantMapper;

    @Test
    void successfulCustomerRegistration() throws Exception {
        // given
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        String userName = userCustomerDTO.getUserName();
        String email = userCustomerDTO.getEmail();
        User user = DomainFixtures.someUser1();
        Customer customer = DomainFixtures.someCustomer1();
        String requestBody = objectMapper.writeValueAsString(userCustomerDTO);

        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);
        Mockito.when(userService.checkIfUserNameExists(userName)).thenReturn(null);
        Mockito.when(userService.checkIfEmailExists(email)).thenReturn(null);

        // when, then
        String endpoint = RegisterRestController.API_REGISTER_PAGE + RegisterRestController.CUSTOMER_DONE;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void successfulCustomerRegistrationUserNameExists() throws Exception {
        // given
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        String userName = userCustomerDTO.getUserName();
        String email = userCustomerDTO.getEmail();
        User user = DomainFixtures.someUser1();

        Customer customer = DomainFixtures.someCustomer1();
        String requestBody = objectMapper.writeValueAsString(userCustomerDTO);

        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);
        Mockito.when(userService.checkIfUserNameExists(userName)).thenReturn(userName);
        Mockito.when(userService.checkIfEmailExists(email)).thenReturn(null);

        // when, then
        String endpoint = RegisterRestController.API_REGISTER_PAGE + RegisterRestController.CUSTOMER_DONE;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void successfulCustomerRegistrationEmailExists() throws Exception {
        // given
        UserCustomerDTO userCustomerDTO = DTOFixtures.someUserCustomer1();
        String userName = userCustomerDTO.getUserName();
        String email = userCustomerDTO.getEmail();
        User user = DomainFixtures.someUser1();

        Customer customer = DomainFixtures.someCustomer1();
        String requestBody = objectMapper.writeValueAsString(userCustomerDTO);

        Mockito.when(userMapper.map(userCustomerDTO)).thenReturn(user);
        Mockito.when(customerMapper.map(userCustomerDTO)).thenReturn(customer);
        Mockito.when(userService.checkIfUserNameExists(userName)).thenReturn(null);
        Mockito.when(userService.checkIfEmailExists(email)).thenReturn(email);

        // when, then
        String endpoint = RegisterRestController.API_REGISTER_PAGE + RegisterRestController.CUSTOMER_DONE;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void successfulRestaurantRegistration() throws Exception {
        // given
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        String userName = userRestaurantDTO.getUserName();
        String email = userRestaurantDTO.getEmail();
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String requestBody = objectMapper.writeValueAsString(userRestaurantDTO);

        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);
        Mockito.when(userService.checkIfUserNameExists(userName)).thenReturn(null);
        Mockito.when(userService.checkIfEmailExists(email)).thenReturn(null);

        // when, then
        String endpoint = RegisterRestController.API_REGISTER_PAGE + RegisterRestController.RESTAURANT_DONE;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void successfulRestaurantRegistrationUserNameExists() throws Exception {
        // given
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        String userName = userRestaurantDTO.getUserName();
        String email = userRestaurantDTO.getEmail();
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String requestBody = objectMapper.writeValueAsString(userRestaurantDTO);

        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);
        Mockito.when(userService.checkIfUserNameExists(userName)).thenReturn(userName);
        Mockito.when(userService.checkIfEmailExists(email)).thenReturn(null);

        // when, then
        String endpoint = RegisterRestController.API_REGISTER_PAGE + RegisterRestController.RESTAURANT_DONE;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void successfulRestaurantRegistrationEmailExists() throws Exception {
        // given
        UserRestaurantDTO userRestaurantDTO = DTOFixtures.someUserRestaurant1();
        String userName = userRestaurantDTO.getUserName();
        String email = userRestaurantDTO.getEmail();
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String requestBody = objectMapper.writeValueAsString(userRestaurantDTO);

        Mockito.when(userMapper.map(userRestaurantDTO)).thenReturn(user);
        Mockito.when(restaurantMapper.mapFromDTO(userRestaurantDTO)).thenReturn(restaurant);
        Mockito.when(userService.checkIfUserNameExists(userName)).thenReturn(null);
        Mockito.when(userService.checkIfEmailExists(email)).thenReturn(email);

        // when, then
        String endpoint = RegisterRestController.API_REGISTER_PAGE + RegisterRestController.RESTAURANT_DONE;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}