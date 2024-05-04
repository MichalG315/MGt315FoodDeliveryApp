package pl.zajavka.domain;

import lombok.*;

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
}
