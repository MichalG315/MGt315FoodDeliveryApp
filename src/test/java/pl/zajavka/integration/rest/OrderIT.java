package pl.zajavka.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.configuration.RestAssuredIntegrationTestBase;
import pl.zajavka.support.OrderControllerTestSupport;
import pl.zajavka.util.DTOFixtures;

public class OrderIT extends RestAssuredIntegrationTestBase implements OrderControllerTestSupport {

    @Test
    void thatOrderWorksCorrectly() {
        // when
        addToCart();
        deleteFromCart();
        addToCart();
        OrderDTO orderDTO = order();
        String orderNumber = orderDTO.getOrderNumber();
        getCustomerOrder();
        deleteCustomerOrder(orderNumber);

        // then
        Assertions.assertThat(orderNumber).isNotNull();
        Assertions.assertThat(orderDTO.getReceivedDateTime()).isNotNull();
    }
}
