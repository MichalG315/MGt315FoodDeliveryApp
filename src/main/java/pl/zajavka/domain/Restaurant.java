package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantName")
@ToString(of = {"restaurantId", "restaurantName", "description"})
public class Restaurant {

    Integer restaurantId;
    String restaurantName;
    String description;
    AddressExtended addressExtended;
    Set<RestaurantDeliveryAddress> restaurantDeliveryAddresses;
    Set<MenuItem> menuItems;
    Set<FoodOrder> foodOrders;

}
