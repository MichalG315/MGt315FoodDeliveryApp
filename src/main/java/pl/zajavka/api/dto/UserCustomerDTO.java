package pl.zajavka.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
                .userName("michal")
                .email("michal@gmail.com")
                .password("test")
                .name("michal")
                .surname("michal")
                .phone("+48 235 987 692")
                .build();
    }
}
