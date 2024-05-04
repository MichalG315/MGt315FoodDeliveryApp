package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userName;
    private String email;
    private String password;
    private Integer role;
    private String name;
    private String surname;
    private String phone;

    public static UserDTO buildDefault() {
        return UserDTO.builder()
                .userName("michal")
                .email("michal@gmail.com")
                .password("test")
                .role(1)
                .name("michal")
                .surname("michal")
                .phone("+48 235 987 692")
                .build();
    }
}
