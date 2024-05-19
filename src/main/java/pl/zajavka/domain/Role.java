package pl.zajavka.domain;

import lombok.*;

@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "role"})
public class Role {
    int id;
    String role;
}
