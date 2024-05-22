package pl.zajavka.api.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.AddressMapper;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;
import pl.zajavka.domain.*;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestaurantRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantRestControllerWebMvcTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private MenuItemService menuItemService;
    @MockBean
    private FoodOrderService foodOrderService;
    @MockBean
    private ImageService imageService;
    @MockBean
    private RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;
    @MockBean
    private MenuItemMapper menuItemMapper;
    @MockBean
    private OrderMapper orderMapper;
    @MockBean
    private AddressMapper addressMapper;

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void getRestaurantMenuItems() throws Exception {
        // given
        String endpoint = RestaurantRestController.API_RESTAURANT_PAGE + RestaurantRestController.MENU +
                RestaurantRestController.RESTAURANT_USER_NAME;
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();


        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);

        Mockito.when(userService.findUserId(userName)).thenReturn(user.getUserId());
        Mockito.when(restaurantService.findRestaurantNameByUserId(user.getUserId()))
                .thenReturn(restaurantName);
        Mockito.when(menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName))
                .thenReturn(List.of(menuItem));
        Mockito.when(menuItemMapper.mapToDTO(menuItem))
                .thenReturn(menuItemDTO);


        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void getYourOrders() throws Exception {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();


        Order order = DomainFixtures.someOrder1(user, restaurant);
        OrderDTO orderDTO = DTOFixtures.someOrderDTO1(user, restaurant);

        Mockito.when(foodOrderService.availableFoodOrdersByRestaurantName(userName))
                .thenReturn(List.of(order));
        Mockito.when(orderMapper.mapToDTO(order)).thenReturn(orderDTO);

        // when, then
        String endpoint = RestaurantRestController.API_RESTAURANT_PAGE + RestaurantRestController.ORDERS +
                RestaurantRestController.RESTAURANT_USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].orderNumber", is(orderDTO.getOrderNumber())))
                .andReturn();
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void patchOrderCompletedDateTime() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        // when, then
        String endpoint = RestaurantRestController.API_RESTAURANT_PAGE + RestaurantRestController.ORDERS +
                RestaurantRestController.DELIVERED + RestaurantRestController.RESTAURANT_USER_NAME +
                RestaurantRestController.ORDER_NUMBER;

        mockMvc.perform(MockMvcRequestBuilders.patch(endpoint, userName, orderNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void addMenuItem() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String requestBody = objectMapper.writeValueAsString(menuItemDTO);

        // when, then
        String endpoint = RestaurantRestController.API_RESTAURANT_PAGE +
                RestaurantRestController.ADD + RestaurantRestController.RESTAURANT_USER_NAME;
        mockMvc.perform(multipart(endpoint, userName)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void deliveryAddressAdded() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        AddressDTO addressDTO = DTOFixtures.someAddress1();
        Address address = DomainFixtures.someAddress1();
        String requestBody = objectMapper.writeValueAsString(addressDTO);

        Mockito.when(addressMapper.mapFromDTO(addressDTO)).thenReturn(address);

        // when, then
        String endpoint = RestaurantRestController.API_RESTAURANT_PAGE +
                RestaurantRestController.ADDRESS + RestaurantRestController.RESTAURANT_USER_NAME +
                RestaurantRestController.ADD;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, userName)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}