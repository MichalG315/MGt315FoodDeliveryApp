package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.api.dto.mapper.CustomerMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.api.dto.mapper.UserMapper;
import pl.zajavka.business.UserService;

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
    void successfulCustomerRegistration() throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserCustomerDTO.buildDefault().asMap().forEach(parameters::add);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_CUSTOMER_DONE).params(parameters))
                .andExpect(status().isOk());
    }

    @Test
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
    void successfulRestaurantRegistration() throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        UserRestaurantDTO.buildDefault().asMap().forEach(parameters::add);

        // when, then
        mockMvc.perform(post(RegisterController.REGISTER_RESTAURANT_DONE).params(parameters))
                .andExpect(status().isOk());
    }
}