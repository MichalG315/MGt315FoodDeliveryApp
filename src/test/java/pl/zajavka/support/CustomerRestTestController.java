package pl.zajavka.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import pl.zajavka.api.controller.rest.CustomerRestController;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.domain.Address;

import java.util.List;

public interface CustomerRestTestController {

    RequestSpecification requestSpecification();

    String getListOfRestaurantEndpoint = CustomerRestController.API_CUSTOMER_PAGE;

    default List<RestaurantDTO> getListOfRestaurant() {
        Address address = Address.builder()
                .country("Poland")
                .city("Warszawa")
                .postalCode("01-949")
                .streetName("Sokratesa")
                .build();

        Pageable pageable = somePageable1();
        int pageSize = pageable.getPageSize();
        String sortField = "restaurantName";
        String sortDir = "asc";
        String streetName = address.getStreetName();
        String city = address.getCity();

        return requestSpecification()
                .queryParam("currentPage", "1")
                .queryParam("pageSize", String.valueOf(pageSize))
                .queryParam("sortField", sortField)
                .queryParam("sortDirection", sortDir)
                .queryParam("streetName", streetName)
                .queryParam("city", city)
                .get(getListOfRestaurantEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .jsonPath()
                .getList(".", RestaurantDTO.class);
    }

    private static Pageable somePageable1() {
        Sort sort = Sort.by("restaurantName").ascending();
        return PageRequest.of(0, 3, sort);
    }

}
