package pl.zajavka.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zajavka.api.controller.rest.RegisterRestController;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;

public interface RegisterRestControllerTestSupport {

    RequestSpecification requestSpecification();

    String successfulCustomerRegistrationEndpoint = RegisterRestController.API_REGISTER_PAGE +
            RegisterRestController.CUSTOMER_DONE;

    String successfulRestaurantRegistrationEndpoint = RegisterRestController.API_REGISTER_PAGE +
            RegisterRestController.RESTAURANT_DONE;

    default void successfulCustomerRegistration() {

        UserCustomerDTO userCustomerDTO = UserCustomerDTO.builder()
                .userName("tomek")
                .email("tomek@gmail.com")
                .password("testpassword")
                .name("tomek")
                .surname("tomek")
                .phone("+48 235 987 692")
                .build();

        requestSpecification()
                .body(userCustomerDTO)
                .post(successfulCustomerRegistrationEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void successfulRestaurantRegistration() {

        UserRestaurantDTO userRestaurantDTO = UserRestaurantDTO.builder()
                .userName("restaurant")
                .email("restaurant@gmail.com")
                .password("testpassword")
                .restaurantName("test restaurant")
                .description("my test restaurant description")
                .addressCountry("Poland")
                .addressCity("Tricity")
                .addressPostalCode("80-180")
                .addressStreetName("Grunwaldzka")
                .addressStreetNumber("10C/89")
                .build();

        requestSpecification()
                .body(userRestaurantDTO)
                .post(successfulRestaurantRegistrationEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
