package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRestaurantDTO {
    private String userName;
    private String email;
    private String password;
    private String restaurantName;
    private String description;
    private String addressCountry;
    private String addressCity;
    private String addressPostalCode;
    private String addressStreetName;
    private String addressStreetNumber;

    public static UserRestaurantDTO buildDefault() {
        return UserRestaurantDTO.builder()
                .userName("restaurant")
                .email("restaurant@gmail.com")
                .password("test")
                .restaurantName("test restaurant")
                .description("my test restaurant description")
                .addressCountry("Poland")
                .addressCity("Tricity")
                .addressPostalCode("80-180")
                .addressStreetName("Grunwaldzka")
                .addressStreetNumber("10C/89")
                .build();
    }


}
