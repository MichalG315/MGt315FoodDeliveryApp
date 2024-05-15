package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.FactsDAO;

@Slf4j
@Service
@AllArgsConstructor
public class FactsService {

    private final FactsDAO factsDAO;

    public String getRandomCatFact(){
        return factsDAO.getRandomCatFact();
    }
}
