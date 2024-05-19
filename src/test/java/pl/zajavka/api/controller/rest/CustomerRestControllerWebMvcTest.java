package pl.zajavka.api.controller.rest;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import pl.zajavka.api.controller.CustomerController;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.RestaurantService;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerRestController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CustomerRestControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private RestaurantMapper restaurantMapper;

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getListOfRestaurant() throws Exception {
        // given
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        PageImpl<Restaurant> restaurants = new PageImpl<>(List.of(restaurant));
        RestaurantDTO restaurantDTO = DTOFixtures.someRestaurantDTO1();

        Pageable pageable = somePageable1();
        int pageSize = pageable.getPageSize();
        String sortField = "restaurantName";
        String sortDir = "asc";
        String streetName = address.getStreetName();
        String city = address.getCity();

        Mockito.when(restaurantService.findAvailable(1, pageSize, sortField, sortDir, streetName, city))
                        .thenReturn(restaurants);
        when(restaurantMapper.mapToDTO(restaurant)).thenReturn(restaurantDTO);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get(CustomerRestController.API_CUSTOMER_PAGE)
                        .param("pageNo", "1")
                        .param("pageSize", String.valueOf(pageSize))
                        .param("sortField", sortField)
                        .param("sortDirection", sortDir)
                        .param("streetName", streetName)
                        .param("city", city))
                .andExpect(status().isOk())
                .andReturn();
    }

    private static Pageable somePageable1() {
        Sort sort = Sort.by("restaurantName").ascending();
        return PageRequest.of(0, 3, sort);
    }
}