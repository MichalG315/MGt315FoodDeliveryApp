package pl.zajavka.infrastructure.catFacts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.FactsDAO;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.catFacts.api.FactsApi;
import pl.zajavka.infrastructure.catFacts.model.CatFact;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FactsImpl implements FactsDAO {

    private final FactsApi factsApi;

    @Override
    public String getRandomCatFact() {
        long factLength = 127;
        Mono<CatFact> randomFact = factsApi.getRandomFact(factLength);
        return Optional.ofNullable(randomFact.block(Duration.of(1000, ChronoUnit.MILLIS)).getFact())
                .orElseThrow(() -> new NotFoundException("Could not find a cat fact"));
    }
}
