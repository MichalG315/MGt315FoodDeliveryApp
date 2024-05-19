package pl.zajavka.util;


import pl.zajavka.api.controller.MenuItemCategories;
import pl.zajavka.domain.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;

public class DomainFixtures {
    public static Address someAddress1() {
        return Address.builder()
                .country("test")
                .city("test")
                .postalCode("test")
                .streetName("test")
                .build();
    }

    public static AddressExtended someAddressExtended1(Address address) {
        return AddressExtended.builder()
                .address(address)
                .streetNumber("2A/1")
                .build();
    }

    public static RestaurantDeliveryAddress someRestaurantDeliveryAddress(Address address, Restaurant restaurant) {
        return RestaurantDeliveryAddress.builder()
                .address(address)
                .restaurant(restaurant)
                .build();
    }

    public static Customer someCustomer1() {
        return Customer.builder()
                .name("test")
                .surname("test")
                .email("test@gmail.com")
                .phone("+48 123 456 789")
                .userId(1)
                .build();
    }

    public static Order someOrder1(User user, Restaurant restaurant) {
        return Order.builder()
                .userName(user.getUserName())
                .restaurantName(restaurant.getRestaurantName())
                .orderNumber("someNumber")
                .menuItemMap(Map.of(someMenuItem1(restaurant), 2L))
                .receivedDateTime(OffsetDateTime.of(10, 10, 10, 10, 10, 10, 10, ZoneOffset.UTC))
                .totalAmount(new BigDecimal(1))
                .status("test status")
                .build();
    }


    public static MenuItem someMenuItem1(Restaurant restaurant) {
        return MenuItem.builder()
                .menuItemNumber("test1")
                .itemName("test1")
                .description("test1")
                .price(new BigDecimal(1))
                .category(MenuItemCategories.BREAKFAST.getToPrint())
                .restaurantName(restaurant.getRestaurantName())
                .build();
    }

    public static Restaurant someRestaurant1() {
        return Restaurant.builder()
                .restaurantName("testowo")
                .description("test test")
                .userId(1)
                .build();
    }

    public static Restaurant someRestaurant2(Address address) {
        return Restaurant.builder()
                .restaurantName("testowo")
                .description("test test")
                .restaurantDeliveryStreetNames(Set.of(address.getStreetName()))
                .userId(1)
                .build();
    }

    public static Restaurant someRestaurant3(AddressExtended addressExtended) {
        return Restaurant.builder()
                .restaurantName("test")
                .description("test")
                .addressStreetNumber(addressExtended.getStreetNumber())
                .userId(1)
                .build();
    }

    public static Restaurant someRestaurant4(AddressExtended addressExtended) {
        return Restaurant.builder()
                .restaurantName("test")
                .description("test")
                .userId(1)
                .addressCountry("test")
                .addressCity("test")
                .addressPostalCode("test")
                .addressStreetName("test")
                .addressStreetNumber("test")
                .build();
    }

    public static User someUser1() {
        return User.builder()
                .userId(1)
                .userName("testUserName")
                .email("test@gmail.com")
                .password("testtesttest")
                .active(true)
                .build();
    }

    public static User someUser2(Role role) {
        return User.builder()
                .userId(1)
                .userName("testUserName")
                .email("test@gmail.com")
                .password("testtesttest")
                .active(true)
                .roles(Set.of(role))
                .build();
    }

    public static Role someRole1() {
        return Role.builder()
                .id(1)
                .role("TEST")
                .build();
    }

    public static Fact someFact1(){
        return Fact.builder()
                .fact("test fact")
                .build();
    }
}
