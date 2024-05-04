package pl.zajavka.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

@With
@Value
@Builder
public class Order {

    String userName;
    String restaurantName;
    String orderNumber;
    Map<MenuItem, Integer> menuItemMap;
    OffsetDateTime receivedDateTime;
    BigDecimal totalAmount;


}
