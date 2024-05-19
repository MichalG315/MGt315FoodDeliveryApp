package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "fact")
@ToString(of = {"fact"})
public class Fact {
    String fact;
}
