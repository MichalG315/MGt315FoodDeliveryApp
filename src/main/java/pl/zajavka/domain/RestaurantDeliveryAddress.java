package pl.zajavka.domain;

import lombok.*;

@Value
@Builder
@EqualsAndHashCode(of = "restaurantDeliveryAddressId")
@ToString(of = {"restaurantDeliveryAddressId"})
public class RestaurantDeliveryAddress {
    Integer restaurantDeliveryAddressId;
    Address address;
    Restaurant restaurant;
}
