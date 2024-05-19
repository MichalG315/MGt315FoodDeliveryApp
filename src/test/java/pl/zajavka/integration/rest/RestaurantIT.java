package pl.zajavka.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.configuration.RestAssuredIntegrationTestBase;
import pl.zajavka.support.OrderRestControllerTestSupport;
import pl.zajavka.support.RestaurantRestControllerTestSupport;

import java.util.List;

public class RestaurantIT extends RestAssuredIntegrationTestBase
        implements RestaurantRestControllerTestSupport, OrderRestControllerTestSupport {

    @Test
    void restaurantPagesWorksCorrectly() {

        // when
        addToCart();
        order();
        List<MenuItemDTO> restaurantMenuItemsBefore = getRestaurantMenuItems();
        List<OrderDTO> yourOrdersBeforeDelivery = getYourOrders();
        String orderNumber = yourOrdersBeforeDelivery.get(0).getOrderNumber();
        patchOrderCompletedDateTime(orderNumber);
        List<OrderDTO> yourOrdersAfterDelivery = getYourOrders();
        addMenuItem();
        List<MenuItemDTO> restaurantMenuItemsAfter = getRestaurantMenuItems();
        deliveryAddressAdded();

        // then
        Assertions.assertThat(restaurantMenuItemsBefore).hasSize(3);
        Assertions.assertThat(yourOrdersAfterDelivery.get(0).getCompletedDateTime()).isNotNull();
        Assertions.assertThat(restaurantMenuItemsAfter).hasSize(4);

    }

}
