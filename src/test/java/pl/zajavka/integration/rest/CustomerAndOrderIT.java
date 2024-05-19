package pl.zajavka.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.configuration.RestAssuredIntegrationTestBase;
import pl.zajavka.support.CustomerRestTestController;
import pl.zajavka.support.OrderRestControllerTestSupport;

import java.util.List;

public class CustomerAndOrderIT extends RestAssuredIntegrationTestBase
        implements OrderRestControllerTestSupport, CustomerRestTestController {

    @Test
    void customerPagesWorksCorrectly() {
        // when
        List<RestaurantDTO> listOfRestaurant = getListOfRestaurant();
        addToCart();
        deleteFromCart();
        addToCart();
        OrderDTO orderDTO = order();
        String orderNumber = orderDTO.getOrderNumber();
        List<OrderDTO> customerOrder = getCustomerOrder();
        deleteCustomerOrder(orderNumber);

        // then
        Assertions.assertThat(orderNumber).isNotNull();
        Assertions.assertThat(orderDTO.getReceivedDateTime()).isNotNull();
        Assertions.assertThat(customerOrder).hasSize(1);
        Assertions.assertThat(listOfRestaurant).hasSize(3);
    }
}
