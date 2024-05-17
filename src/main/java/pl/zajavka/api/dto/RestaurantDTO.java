package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private String restaurantName;
    private String description;
    private String addressCountry;
    private String addressCity;
    private String addressPostalCode;
    private String addressStreetName;
    private String addressStreetNumber;
    private String completeAddress;
    private Set<String> restaurantDeliveryStreetNames;
}
