package pl.zajavka.api.controller;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@WebMvcTest(controllers = RestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
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
    @WithMockUser(roles = "RESTAURANT")
    void getRestaurantPage() throws Exception {
        //given, when, then
        mockMvc.perform(MockMvcRequestBuilders.get(RestaurantController.RESTAURANT_PAGE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("restaurant_page"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void getMenuItemsPage() throws Exception {
        // given
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
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.MENU +
                RestaurantController.RESTAURANT_USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("menuItemDTOs"))
                .andExpect(MockMvcResultMatchers.view().name("restaurant_menu_items"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
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
    void patchOrderCompletedDateTime() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        // when, then
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.ORDERS +
                RestaurantController.DELIVERED + RestaurantController.RESTAURANT_USER_NAME +
                RestaurantController.ORDER_NUMBER;

        mockMvc.perform(MockMvcRequestBuilders.patch(endpoint, userName, orderNumber))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void getAddMenuItem() throws Exception {
        // when, then
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.ADD;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("menuItemDTO"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("CategoriesDTO"))
                .andExpect(MockMvcResultMatchers.view().name("restaurant_add_menu_item"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void addMenuItem() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        MockMultipartFile file = new MockMultipartFile(
                "image",
                "image.txt",
                ContentType.TEXT_PLAIN.toString(),
                "test text".getBytes()
        );


        // when, then
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.ADD +
                RestaurantController.RESTAURANT_USER_NAME;

        mockMvc.perform(multipart(endpoint, userName, menuItemDTO).file(file))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void getAddAddress() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        // when, then
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.ADDRESS +
                RestaurantController.RESTAURANT_USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("addressDTO"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deliveryAddresses"))
                .andExpect(MockMvcResultMatchers.view().name("restaurant_add_delivery_address"));
    }

    @Test
    @WithMockUser(roles = "RESTAURANT")
    void deliveryAddressAdded() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        AddressDTO addressDTO = DTOFixtures.someAddress1();
        Address address = DomainFixtures.someAddress1();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("country", "test");
        parameters.add("city", "test");
        parameters.add("postalCode", "test");
        parameters.add("streetName", "test");

        Mockito.when(addressMapper.mapFromDTO(addressDTO)).thenReturn(address);

        // when, then
        String endpoint = RestaurantController.RESTAURANT_PAGE + RestaurantController.ADDRESS +
                RestaurantController.RESTAURANT_USER_NAME + RestaurantController.ADD;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, userName).params(parameters))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
}