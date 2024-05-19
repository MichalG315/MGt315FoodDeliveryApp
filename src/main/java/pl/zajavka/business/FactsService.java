package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.dao.FactsDAO;
import pl.zajavka.domain.Fact;

@Slf4j
@Service
@AllArgsConstructor
public class FactsService {

    private final FactsDAO factsDAO;

    @Transactional
    public Fact getRandomCatFact() {
        return factsDAO.getRandomCatFact();
    }
}
