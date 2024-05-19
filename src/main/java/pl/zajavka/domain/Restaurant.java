package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.Set;

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
