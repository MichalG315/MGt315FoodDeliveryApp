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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.FactMapper;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;
import pl.zajavka.domain.*;
import pl.zajavka.util.DTOFixtures;
import pl.zajavka.util.DomainFixtures;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = OrderRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class OrderRestControllerWebMvcTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

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
    private FactMapper factMapper;

    @MockBean
    private MenuItemMapper menuItemMapper;
    @MockBean
    private OrderMapper orderMapper;

    @Test
    void addToCart() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderRestController.API_ORDER +
                OrderRestController.MENU_ITEM_NUMBER + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void addToCartSecond() throws Exception {
        // given
        addToCart();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderRestController.API_ORDER +
                OrderRestController.MENU_ITEM_NUMBER + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deleteFromCart() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderRestController.API_ORDER +
                OrderRestController.MENU_ITEM_NUMBER + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteFromCartSecond() throws Exception {
        // given
        addToCart();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        MenuItemDTO menuItemDTO = DTOFixtures.someMenuItem1(restaurant);
        String menuItemNumber = menuItemDTO.getMenuItemNumber();
        MenuItem menuItem = DomainFixtures.someMenuItem1(restaurant);
        Mockito.when(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber))
                .thenReturn(menuItem);
        Mockito.when(menuItemMapper.mapToDTO(menuItem)).thenReturn(menuItemDTO);

        // when, then
        String endpoint = OrderRestController.API_ORDER +
                OrderRestController.MENU_ITEM_NUMBER + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, menuItemNumber, userName))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void submitOrder() throws Exception {
        // given
        addToCart();
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getCity())));
        Mockito.when(restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getStreetName())));
        String requestBody = objectMapper.writeValueAsString(address);

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.RESTAURANT_NAME +
                OrderRestController.SUBMIT + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, userName)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void submitOrderSecond() throws Exception {
        // given
        addToCart();
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getCity())));
        Mockito.when(restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getStreetName())));
        String requestBody = objectMapper.writeValueAsString(Address.builder()
                .country("badtestparam")
                .city("badtestparam")
                .postalCode("badtestparam")
                .streetName("badtestparam")
                .build());

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.RESTAURANT_NAME +
                OrderRestController.SUBMIT + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, userName)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void submitOrderThird() throws Exception {
        // given
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getCity())));
        Mockito.when(restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getStreetName())));
        String requestBody = objectMapper.writeValueAsString(address);

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.RESTAURANT_NAME +
                OrderRestController.SUBMIT + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, userName)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void submitOrderForth() throws Exception {
        // given
        addToCart();
        deleteFromCart();
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);
        String restaurantName = restaurant.getRestaurantName();
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();

        Mockito.when(restaurantDeliveryAddressesService.findCitesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getCity())));
        Mockito.when(restaurantDeliveryAddressesService.findStreetNamesByRestaurantName(restaurantName))
                .thenReturn(new HashSet<>(Set.of(address.getStreetName())));
        String requestBody = objectMapper.writeValueAsString(address);

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.RESTAURANT_NAME +
                OrderRestController.SUBMIT + OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint, restaurantName, userName)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getCustomerOrders() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        String userName = user.getUserName();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        OrderDTO orderDTO = DTOFixtures.someOrderDTO1(user, restaurant);

        Mockito.when(foodOrderService.availableFoodOrdersByUserName(userName))
                .thenReturn(List.of(order));
        Mockito.when(orderMapper.mapToDTO(order)).thenReturn(orderDTO);

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.CUSTOMER_ORDERS +
                OrderRestController.USER_NAME;

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint, userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].orderNumber", is(orderDTO.getOrderNumber())));
    }

    @Test
    void deleteOrder() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        Mockito.when(foodOrderService.findFoodOrderReceivedDateTime(orderNumber))
                .thenReturn(OffsetDateTime.now());

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.CUSTOMER_ORDERS +
                OrderRestController.DELETE + OrderRestController.ORDER_NUMBER;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, orderNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteOrderSecond() throws Exception {
        // given
        User user = DomainFixtures.someUser1();
        Restaurant restaurant = DomainFixtures.someRestaurant1();
        Order order = DomainFixtures.someOrder1(user, restaurant);
        String orderNumber = order.getOrderNumber();

        Mockito.when(foodOrderService.findFoodOrderReceivedDateTime(orderNumber))
                .thenReturn(OffsetDateTime.now().minusMinutes(25L));

        // when, then
        String endpoint = OrderRestController.API_ORDER + OrderRestController.CUSTOMER_ORDERS +
                OrderRestController.DELETE + OrderRestController.ORDER_NUMBER;

        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint, orderNumber))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}