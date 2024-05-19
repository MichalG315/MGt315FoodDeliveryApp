package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"userName", "email"})
public class User {
    String userName;
    String email;
    @With
    String password;
    @With
    Boolean active;
    @With
    Integer role;
    Integer userId;
    @With
    Set<Role> roles;
}
