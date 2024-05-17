package pl.zajavka.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantDeliveryAddressId")
@ToString(of = {"restaurantDeliveryAddressId"})
public class RestaurantDeliveryAddress {
    Integer restaurantDeliveryAddressId;
    Address address;
    Restaurant restaurant;
}
