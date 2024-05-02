package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "streetName"})

public class Address {

    Integer addressId;
    String country;
    String city;
    String postalCode;
    String streetName;
    Set<AddressExtended> addressesExtended;
    Set<RestaurantDeliveryAddress> restaurantDeliveryAddresses;

}
