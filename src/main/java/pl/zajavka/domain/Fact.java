package pl.zajavka.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "fact")
@ToString(of = {"fact"})
public class Fact {
    String fact;
}
