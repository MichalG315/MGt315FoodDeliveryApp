package pl.zajavka.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
public class UserCustomerDTO {
    @Length(min = 5, max = 64)
    private String userName;
    @Email
    private String email;
    @Length(min = 8, max = 128)
    private String password;
    private String name;
    private String surname;
    @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
    private String phone;

    public static UserCustomerDTO buildDefault() {
        return UserCustomerDTO.builder()
                .userName("tomek")
                .email("tomek@gmail.com")
                .password("testpassword")
                .name("tomek")
                .surname("tomek")
                .phone("+48 235 987 692")
                .build();
    }

    public Map<String, String> asMap() {
        Map<String, String> result = new HashMap<>();
        ofNullable(userName).ifPresent(value -> result.put("userName", value));
        ofNullable(email).ifPresent(value -> result.put("email", value));
        ofNullable(password).ifPresent(value -> result.put("password", value));
        ofNullable(name).ifPresent(value -> result.put("name", value));
        ofNullable(surname).ifPresent(value -> result.put("surname", value));
        ofNullable(phone).ifPresent(value -> result.put("phone", value));
        return result;
    }
}
