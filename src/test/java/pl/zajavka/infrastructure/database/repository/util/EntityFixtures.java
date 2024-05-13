package pl.zajavka.infrastructure.database.repository.util;

import pl.zajavka.api.controller.MenuItemCategories;
import pl.zajavka.infrastructure.database.entity.*;
import pl.zajavka.infrastructure.security.entity.UserEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

public class EntityFixtures {

    public static AddressEntity someAddress1() {
        return AddressEntity.builder()
                .country("test")
                .city("test")
                .postalCode("test")
                .streetName("test")
                .build();
    }

    public static AddressExtendedEntity someAddressExtended1(AddressEntity address) {
        return AddressExtendedEntity.builder()
                .address(address)
                .streetNumber("2A/1")
                .build();
    }

    public static RestaurantDeliveryAddressEntity someRestaurantDeliveryAddress1(AddressEntity address, RestaurantEntity restaurant){
        return RestaurantDeliveryAddressEntity.builder()
                .address(address)
                .restaurant(restaurant)
                .build();
    }

    public static CustomerEntity someCustomer1() {
        return CustomerEntity.builder()
                .name("test")
                .surname("test")
                .email("test@gmail.com")
                .phone("+48 123 456 789")
                .userId(1)
                .build();
    }

    public static Optional<CustomerEntity> someCustomer2() {
        CustomerEntity customer = CustomerEntity.builder()
                .name("test")
                .surname("test")
                .email("test@gmail.com")
                .phone("+48 123 456 789")
                .userId(1)
                .build();
        return Optional.of(customer);
    }

    public static Optional<CustomerEntity> someCustomer3() {
        CustomerEntity customer = CustomerEntity.builder()
                .name("test")
                .surname("test")
                .phone("+48 123 456 789")
                .userId(1)
                .build();
        return Optional.of(customer);
    }

    public static RestaurantEntity someRestaurant1(AddressExtendedEntity addressExtended) {
        return RestaurantEntity.builder()
                .restaurantName("testowo")
                .description("test test")
                .addressExtended(addressExtended)
                .userId(1)
                .build();
    }

    public static Optional<RestaurantEntity> someRestaurant2(AddressExtendedEntity addressExtended) {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantName("testowo")
                .description("test test")
                .addressExtended(addressExtended)
                .userId(1)
                .build();
        return Optional.of(restaurant);
    }

    public static FoodOrderEntity someFoodOrderEntity1(CustomerEntity customer, RestaurantEntity restaurant) {
        return FoodOrderEntity.builder()
                .foodOrderNumber("testNumber")
                .receivedDateTime(OffsetDateTime.now())
                .totalAmount(new BigDecimal("123"))
                .Status(MenuItemCategories.BREAKFAST.getToPrint())
                .customer(customer)
                .restaurant(restaurant)
                .build();
    }

    public static MenuItemEntity someMenuItem1(RestaurantEntity restaurant) {
        return MenuItemEntity.builder()
                .menuItemNumber("test1")
                .itemName("test1")
                .description("test1")
                .price(new BigDecimal(1))
                .category(MenuItemCategories.BREAKFAST.getToPrint())
                .restaurant(restaurant)
                .build();
    }

    public static MenuItemEntity someMenuItem2(RestaurantEntity restaurant) {
        return MenuItemEntity.builder()
                .menuItemNumber("test2")
                .itemName("test2")
                .description("test2")
                .price(new BigDecimal(2))
                .category(MenuItemCategories.BURGER.getToPrint())
                .restaurant(restaurant)
                .build();
    }

    public static MenuItemEntity someMenuItem3(RestaurantEntity restaurant) {
        return MenuItemEntity.builder()
                .menuItemId(1)
                .menuItemNumber("test number")
                .itemName("test name")
                .description("test description")
                .price(new BigDecimal(1))
                .category("test category")
                .build();
    }


    public static UserEntity someUser1() {
        return UserEntity.builder()
                .id(1)
                .userName("testUserName")
                .email("test@gmail.com")
                .password("testtesttest")
                .active(true)
                .build();
    }

    public static MenuItemFoodOrderEntity someMenuItemFoodOrder1(MenuItemEntity menuItem, FoodOrderEntity foodOrder) {
        return MenuItemFoodOrderEntity.builder()
                .menuItemFoodOrderId(1)
                .quantity(1)
                .menuItem(menuItem)
                .foodOrder(foodOrder)
                .build();
    }

}
