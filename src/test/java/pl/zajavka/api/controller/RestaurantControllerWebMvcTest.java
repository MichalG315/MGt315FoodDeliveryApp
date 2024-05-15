package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.AddressMapper;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;
import pl.zajavka.domain.Order;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

import java.util.List;

@WebMvcTest(controllers = RestaurantController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantControllerWebMvcTest {

    private final MockMvc mockMvc;

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
    @WithMockUser(roles = "CUSTOMER")
    void getRestaurantPage() throws Exception {
        //given, when, then
        mockMvc.perform(MockMvcRequestBuilders.get(RestaurantController.RESTAURANT_PAGE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("restaurant_page"));
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getMenuItemsPage() throws Exception {

    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getYourOrders() throws Exception {
        // given
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();


        Order order = DomainFixtures.someOrder1(user, restaurant);
        OrderDTO orderDTO = DTOFixtures.someOrderDTO1(user, restaurant);

        Mockito.when(foodOrderService.availableFoodOrdersByRestaurantName(restaurantName))
                .thenReturn(List.of(order));
        Mockito.when(orderMapper.mapToDTO(order))
                .thenReturn(orderDTO);

        // when, then
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.ORDERS +
                RestaurantController.RESTAURANT_USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("availableCustomerOrderDTOs"))
                .andExpect(MockMvcResultMatchers.view().name("restaurant_your_orders"));
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void patchOrderCompletedDateTime() {
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getAddMenuItem() {
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void addMenuItem() {
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getAddAddress() {
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void deliveryAddressAdded() {
    }
}