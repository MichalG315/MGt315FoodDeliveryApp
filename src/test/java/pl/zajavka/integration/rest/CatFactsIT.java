package pl.zajavka.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zajavka.api.dto.FactDTO;
import pl.zajavka.configuration.RestAssuredIntegrationTestBase;
import pl.zajavka.support.CatFactTestSupport;
import pl.zajavka.support.WiremockTestSupport;

public class CatFactsIT extends RestAssuredIntegrationTestBase implements CatFactTestSupport, WiremockTestSupport {

    @Test
    void getRandomCatFactTest() {
        // given
        stubForCatFact(wireMockServer);

        // when
        FactDTO randomCatFact = getRandomCatFact();

        Assertions.assertThat(randomCatFact.getFact()).isNotNull();
    }

}
