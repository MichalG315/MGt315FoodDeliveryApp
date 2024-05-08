package pl.zajavka.api.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRestaurantDTO {
    @Length(min = 5, max = 64)
    private String userName;
    @Email
    private String email;
    @Length(min = 8, max = 128)
    private String password;
    @Length(min = 5, max = 32)
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
