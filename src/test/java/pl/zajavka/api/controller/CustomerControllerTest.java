package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.RestaurantDeliveryAddressesService;
import pl.zajavka.business.RestaurantService;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CustomerControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    RestaurantService restaurantService;
    @MockBean
    RestaurantMapper restaurantMapper;
    @MockBean
    RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;

    @Test
    public void getCustomerPagePaginatedAndSortedWorksCorrectly() {


    }

}