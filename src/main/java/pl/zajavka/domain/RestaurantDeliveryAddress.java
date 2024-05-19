package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "restaurantDeliveryAddressId")
@ToString(of = {"restaurantDeliveryAddressId"})
public class RestaurantDeliveryAddress {
    Integer restaurantDeliveryAddressId;
    Address address;
    Restaurant restaurant;
}
