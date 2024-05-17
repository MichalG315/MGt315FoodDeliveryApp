package pl.zajavka.api.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

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
                .password("testpassword")
                .restaurantName("test restaurant")
                .description("my test restaurant description")
                .addressCountry("Poland")
                .addressCity("Tricity")
                .addressPostalCode("80-180")
                .addressStreetName("Grunwaldzka")
                .addressStreetNumber("10C/89")
                .build();
    }

    public Map<String, String> asMap() {
        Map<String, String> result = new HashMap<>();
        ofNullable(userName).ifPresent(value -> result.put("userName", value));
        ofNullable(email).ifPresent(value -> result.put("email", value));
        ofNullable(password).ifPresent(value -> result.put("password", value));
        ofNullable(restaurantName).ifPresent(value -> result.put("restaurantName", value));
        ofNullable(description).ifPresent(value -> result.put("description", value));
        ofNullable(addressCountry).ifPresent(value -> result.put("addressCountry", value));
        ofNullable(addressCity).ifPresent(value -> result.put("addressCity", value));
        ofNullable(addressPostalCode).ifPresent(value -> result.put("addressPostalCode", value));
        ofNullable(addressStreetName).ifPresent(value -> result.put("addressStreetName", value));
        ofNullable(addressStreetNumber).ifPresent(value -> result.put("addressStreetNumber", value));
        return result;
    }
}
