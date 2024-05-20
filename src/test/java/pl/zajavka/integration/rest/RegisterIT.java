package pl.zajavka.integration.rest;

import org.junit.jupiter.api.Test;
import pl.zajavka.configuration.RestAssuredIntegrationTestBase;
import pl.zajavka.support.RegisterRestControllerTestSupport;

public class RegisterIT extends RestAssuredIntegrationTestBase
        implements RegisterRestControllerTestSupport {

    @Test
    void registerWorksCorrectly() {

        // when
        successfulCustomerRegistration();
        successfulRestaurantRegistration();
    }

}
