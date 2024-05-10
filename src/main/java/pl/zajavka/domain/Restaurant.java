package pl.zajavka.domain;

import lombok.*;

import java.util.List;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantName")
@ToString(of = {"restaurantName", "description"})
public class Restaurant {
    String restaurantName;
    String description;
    Integer userId;
    String addressCountry;
    String addressCity;
    String addressPostalCode;
    String addressStreetName;
    String addressStreetNumber;
    String completeAddress;
    Set<String> restaurantDeliveryStreetNames;
}
