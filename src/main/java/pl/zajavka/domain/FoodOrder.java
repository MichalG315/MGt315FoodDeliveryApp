package pl.zajavka.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "foodOrderNumber")
@ToString(of = {"foodOrderId", "foodOrderNumber", "receivedDateTime", "completedDateTime", "customerComment", "totalAmount"})
public class FoodOrder {

    Integer foodOrderId;
    String foodOrderNumber;
    OffsetDateTime receivedDateTime;
    OffsetDateTime completedDateTime;
    String customerComment;
    BigDecimal totalAmount;
    Customer customer;
    Restaurant restaurant;
    Set<MenuItemFoodOrder> menuItemFoodOrders;

}
