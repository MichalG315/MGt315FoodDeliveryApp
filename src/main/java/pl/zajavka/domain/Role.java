package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "role"})
public class Role {
    int id;
    String role;
}
