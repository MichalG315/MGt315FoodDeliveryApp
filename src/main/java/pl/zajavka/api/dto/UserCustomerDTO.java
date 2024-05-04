package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomerDTO {
    private String userName;
    private String email;
    private String password;
    private String name;
    private String surname;
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
