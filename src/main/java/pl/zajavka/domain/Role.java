package pl.zajavka.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "role"})
public class Role {
    int id;
    String role;
}
