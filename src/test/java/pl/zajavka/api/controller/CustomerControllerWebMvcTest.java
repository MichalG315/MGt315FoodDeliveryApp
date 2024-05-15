package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.RestaurantDeliveryAddressesService;
import pl.zajavka.business.RestaurantService;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = CustomerController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CustomerControllerWebMvcTest {

    private final MockMvc mockMvc;

    @MockBean
    RestaurantService restaurantService;
    @MockBean
    RestaurantMapper restaurantMapper;
    @MockBean
    RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getCustomerPagePaginatedAndSortedWorksCorrectly() throws Exception {
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

        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("currentPage", "1");
        parameters.add("pageSize", String.valueOf(pageSize));
        parameters.add("sortField", sortField);
        parameters.add("sortDirection", sortDir);
        parameters.add("streetName", streetName);
        parameters.add("city", city);

        when(restaurantService.findAvailable(1, pageSize, sortField, sortDir, streetName, city))
                .thenReturn(restaurants);
        when(restaurantMapper.mapToDTO(restaurant)).thenReturn(restaurantDTO);
        when(restaurantDeliveryAddressesService.findAvailableCities())
                .thenReturn(Set.of(address.getCity()));
        when(restaurantDeliveryAddressesService.findAvailableStreetNames())
                .thenReturn(Set.of(address.getStreetName()));


        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER_PAGE)
                        .params(parameters))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("currentPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pageSize"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sortField"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sortDirection"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("reverseSortDirection"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("streetName"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("city"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalPages"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalItems"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("availableRestaurantDTOs"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("citiesDTO"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("streetNamesDTO"))
                .andExpect(view().name("customer_page"));
    }

    private static Pageable somePageable1() {
        Sort sort = Sort.by("restaurantName").ascending();
        return PageRequest.of(0, 3, sort);
    }

}