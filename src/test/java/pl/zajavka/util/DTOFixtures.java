package pl.zajavka.util;

import pl.zajavka.api.controller.MenuItemCategories;
import pl.zajavka.api.dto.*;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DTOFixtures {
    public static MenuItemDTO someMenuItem1(Restaurant restaurant) {
        return MenuItemDTO.builder()
                .menuItemNumber("test1")
                .itemName("test1")
                .description("test1")
                .price(new BigDecimal(1))
                .category(MenuItemCategories.BREAKFAST.getToPrint())
                .restaurantName(restaurant.getRestaurantName())
                .build();
    }

    public static RestaurantDTO someRestaurantDTO1() {
        return RestaurantDTO.builder()
                .restaurantName("test")
                .description("test")
                .addressCountry("test")
                .addressCity("test")
                .addressPostalCode("test")
                .addressStreetName("test")
                .addressStreetNumber("test")
                .completeAddress("test test")
                .restaurantDeliveryStreetNames(Set.of("test", "test2"))
                .build();
    }

    public static UserCustomerDTO someUserCustomer1() {
        return UserCustomerDTO.builder()
                .userName("michal")
                .email("michal@gmail.com")
                .password("testpassword")
                .name("michal")
                .surname("michal")
                .phone("+48 235 987 692")
                .build();
    }

    public static UserRestaurantDTO someUserRestaurant1() {
        return UserRestaurantDTO.builder()
                .userName("restaurant")
                .email("restaurant@gmail.com")
                .password("testpassword")
                .restaurantName("test restaurant")
                .description("my test restaurant description")
                .addressCountry("Poland")
                .addressCity("Tricity")
                .addressPostalCode("80-180")
                .addressStreetName("Grunwaldzka")
                .addressStreetNumber("10C/89")
                .build();
    }

    public static OrderDTO someOrderDTO1(User user, Restaurant restaurant) {
        return OrderDTO.builder()
                .userName(user.getUserName())
                .restaurantName(restaurant.getRestaurantName())
                .orderNumber("someNumber")
                .menuItemList(List.of(someMenuItem1(restaurant)))
                .totalAmount(new BigDecimal(1))
                .status("test status")
                .build();
    }

    public static AddressDTO someAddress1() {
        return AddressDTO.builder()
                .country("test")
                .city("test")
                .postalCode("test")
                .streetName("test")
                .build();
    }
}
