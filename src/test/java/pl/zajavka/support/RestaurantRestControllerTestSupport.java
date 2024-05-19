package pl.zajavka.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zajavka.api.controller.rest.RestaurantRestController;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRestControllerTestSupport {

    RequestSpecification requestSpecification();

    String getRestaurantMenuItemsEndpoint = RestaurantRestController.API_RESTAURANT_PAGE +
            RestaurantRestController.MENU + RestaurantRestController.RESTAURANT_USER_NAME;

    String getYourOrdersEndpoint = RestaurantRestController.API_RESTAURANT_PAGE +
            RestaurantRestController.ORDERS + RestaurantRestController.RESTAURANT_USER_NAME;

    String patchOrderCompletedDateTimeEndpoint = RestaurantRestController.API_RESTAURANT_PAGE +
            RestaurantRestController.ORDERS + RestaurantRestController.DELIVERED +
            RestaurantRestController.ORDER_NUMBER;

    String addMenuItemEndpoint = RestaurantRestController.API_RESTAURANT_PAGE +
            RestaurantRestController.ADD + RestaurantRestController.RESTAURANT_USER_NAME;

    String deliveryAddressAddedEndpoint = RestaurantRestController.API_RESTAURANT_PAGE +
            RestaurantRestController.ADDRESS + RestaurantRestController.RESTAURANT_USER_NAME +
            RestaurantRestController.ADD;

    default List<MenuItemDTO> getRestaurantMenuItems() {
        return requestSpecification()
                .pathParam("restaurantUserName", "restaurant_testowy1")
                .get(getRestaurantMenuItemsEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .jsonPath()
                .getList(".", MenuItemDTO.class);
    }

    default List<OrderDTO> getYourOrders() {
        return requestSpecification()
                .pathParam("restaurantUserName", "restaurant_testowy1")
                .get(getYourOrdersEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .jsonPath()
                .getList(".", OrderDTO.class);
    }

    default void patchOrderCompletedDateTime(String orderNumber) {
        requestSpecification()
                .pathParam("orderNumber", orderNumber)
                .patch(patchOrderCompletedDateTimeEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void addMenuItem() {
        MenuItemDTO newMenuItem = MenuItemDTO.builder()
                .itemName("someNewItem")
                .description("someNewItemDescription")
                .price(new BigDecimal(50))
                .category("Burger")
                .restaurantName("Pizza center")
                .build();

        requestSpecification()
                .pathParam("restaurantUserName", "restaurant_testowy1")
                .body(newMenuItem)
                .post(addMenuItemEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    default void deliveryAddressAdded() {

        AddressDTO addressDTO = AddressDTO.builder()
                .country("Poland")
                .city("Gdynia")
                .postalCode("02-919")
                .streetName("Mickiewicza")
                .build();

        requestSpecification()
                .pathParam("restaurantUserName", "restaurant_testowy1")
                .body(addressDTO)
                .post(deliveryAddressAddedEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
