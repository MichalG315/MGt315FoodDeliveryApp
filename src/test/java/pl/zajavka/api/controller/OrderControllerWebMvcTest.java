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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.*;
import pl.zajavka.domain.*;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class OrderControllerWebMvcTest {

    private final MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private MenuItemService menuItemService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private FoodOrderService foodOrderService;
    @MockBean
    private RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;
    @MockBean
    private FactsService factsService;
    @MockBean
    private RestaurantMapper restaurantMapper;
    @MockBean
    private MenuItemMapper menuItemMapper;
    @MockBean
    private OrderMapper orderMapper;

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void showRestaurantPage() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        RestaurantDTO restaurantDTO = DTOFixtures.someRestaurantDTO1();

        Mockito.when(restaurantService.findByRestaurantName(restaurantName))
                .thenReturn(restaurant);
        Mockito.when(restaurantMapper.mapToDTO(restaurant))
                .thenReturn(restaurantDTO);

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, restaurantName, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("restaurantDTO"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("menuItemDTOs"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("streetNames"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cities"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("addressDTO"))
                .andExpect(MockMvcResultMatchers.view().name("order"));
    }

    @Test
    void addToCart() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.MENU_ITEM_NUMBER + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
    @Test
    void addToCartSecond() throws Exception {
        // given
        addToCart();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.MENU_ITEM_NUMBER + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    void deleteFromCart() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.MENU_ITEM_NUMBER + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, restaurantName, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    void deleteFromCartSecond() throws Exception {
        // given
        addToCart();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        String restaurantName = restaurant.getRestaurantName();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.MENU_ITEM_NUMBER + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, restaurantName, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void submitOrder() throws Exception {
        // given
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("country", "test");
        parameters.add("city", "test");
        parameters.add("postalCode", "test");
        parameters.add("streetName", "test");

        Mockito.when(restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getCity())));
        Mockito.when(restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getStreetName())));

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.SUBMIT + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, userName).params(parameters))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void submitOrderSecond() throws Exception {
        // given
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("country", "notMatchValue");
        parameters.add("city", "notMatchValue");
        parameters.add("postalCode", "notMatchValue");
        parameters.add("streetName", "notMatchValue");

        Mockito.when(restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getCity())));
        Mockito.when(restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getStreetName())));

        // when, then
        String endpoint = OrderController.ORDER + OrderController.RESTAURANT_NAME +
                OrderController.SUBMIT + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, userName).params(parameters))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("error_customer_address"));
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getCustomerOrders() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(factsService.getRandomCatFact()).thenReturn("test string");

        // when, then
        String endpoint = OrderController.CUSTOMER_ORDERS + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("availableCustomerOrderDTOs"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("randomCatFact"))
                .andExpect(MockMvcResultMatchers.view().name("customer_page_orders"));
    }

    @Test
    void deleteOrder() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        Mockito.when(foodOrderService.findFoodOrderReceivedDateTime(orderNumber))
                .thenReturn(OffsetDateTime.now());

        // when, then
        String endpoint = OrderController.CUSTOMER_ORDERS + OrderController.DELETE +
                OrderController.ORDER_NUMBER + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, orderNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void deleteOrderSecond() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        Mockito.when(foodOrderService.findFoodOrderReceivedDateTime(orderNumber))
                .thenReturn(OffsetDateTime.now().minusMinutes(25L));

        // when, then
        String endpoint = OrderController.CUSTOMER_ORDERS + OrderController.DELETE +
                OrderController.ORDER_NUMBER + OrderController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, orderNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("error_delete_order"));
    }
}