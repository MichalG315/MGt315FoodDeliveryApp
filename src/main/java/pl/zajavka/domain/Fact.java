package pl.zajavka.domain;

import lombok.*;

@Value
@Builder
@EqualsAndHashCode(of = "fact")
@ToString(of = {"fact"})
public class Fact {
    String fact;
}
