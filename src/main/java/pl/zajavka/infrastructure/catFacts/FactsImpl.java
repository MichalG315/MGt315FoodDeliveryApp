package pl.zajavka.infrastructure.catFacts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.FactsDAO;
import pl.zajavka.domain.Fact;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.catFacts.api.FactsApi;
import pl.zajavka.infrastructure.catFacts.model.CatFact;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FactsImpl implements FactsDAO {

    private final FactsApi factsApi;

    @Override
    public Fact getRandomCatFact() {
        long factLength = 127;
        Mono<CatFact> randomFact = factsApi.getRandomFact(factLength);
        String fact = Optional.ofNullable(randomFact.block().getFact())
                .orElseThrow(() -> new NotFoundException("Could not find a cat fact"));
        return Fact.builder()
                .fact(fact)
                .build();
    }
}
