package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"userName", "email"})
public class User {

    String userName;
    String email;
    String password;
    Boolean active;
    Integer role;
    String name;
    String surname;
    String phone;
    Integer userId;
    Set<Role> roles;
}
