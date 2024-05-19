package pl.zajavka.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zajavka.api.controller.OrderController;
import pl.zajavka.api.controller.rest.OrderRestController;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;

public interface OrderControllerTestSupport {

    RequestSpecification requestSpecification();

    String orderEndpoint = OrderRestController.API_ORDER + OrderController.RESTAURANT_NAME
            + OrderController.SUBMIT + OrderController.USER_NAME;

    String addToCartEndpoint = OrderRestController.API_ORDER + OrderRestController.MENU_ITEM_NUMBER +
            OrderRestController.USER_NAME;

    String deleteFromCartEndpoint = OrderRestController.API_ORDER +
            OrderRestController.MENU_ITEM_NUMBER + OrderRestController.USER_NAME;

    String getCustomerOrderEndpoint = OrderRestController.API_ORDER + OrderRestController.CUSTOMER_ORDERS +
            OrderRestController.USER_NAME;

    String deleteOrderEndpoint = OrderRestController.API_ORDER + OrderRestController.CUSTOMER_ORDERS +
            OrderRestController.DELETE + OrderRestController.ORDER_NUMBER;

    default OrderDTO order() {

        AddressDTO addressDTO = AddressDTO.builder()
                .country("Poland")
                .city("Warszawa")
                .postalCode("01-949")
                .streetName("Sokratesa")
                .build();

        return requestSpecification()
                .pathParam("restaurantName", "Pizza center")
                .pathParam("userName", "customer_testowy")
                .body(addressDTO)
                .post(orderEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(OrderDTO.class);
    }

    default void addToCart() {
        requestSpecification()
                .pathParam("menuItemNumber", "Piz1")
                .pathParam("userName", "customer_testowy")
                .post(addToCartEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteFromCart(){
        requestSpecification()
                .pathParam("menuItemNumber", "Piz1")
                .pathParam("userName", "customer_testowy")
                .delete(deleteFromCartEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void getCustomerOrder(){
        requestSpecification()
                .pathParam("userName", "customer_testowy")
                .get(getCustomerOrderEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteCustomerOrder(String orderNumber){
        requestSpecification()
                .pathParam("orderNumber", orderNumber)
                .delete(deleteOrderEndpoint)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
