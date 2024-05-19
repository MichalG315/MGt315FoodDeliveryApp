package pl.zajavka.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zajavka.api.controller.rest.OrderRestController;
import pl.zajavka.api.dto.FactDTO;

public interface CatFactTestSupport {

    RequestSpecification requestSpecification();

    default FactDTO getRandomCatFact(){
        return requestSpecification()
                .get(OrderRestController.API_ORDER + "/random/cat/fact")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(FactDTO.class);
    }

}
