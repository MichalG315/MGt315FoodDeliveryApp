package pl.zajavka.api.dto;

import lombok.*;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.MenuItemFoodOrder;
import pl.zajavka.domain.Restaurant;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrderDTO {

    private String foodOrderNumber;
    private OffsetDateTime receivedDateTime;
    private OffsetDateTime completedDateTime;
    private String customerComment;
    private BigDecimal totalAmount;
    private Customer customer;
    private Restaurant restaurant;
    private Set<MenuItemFoodOrder> menuItemFoodOrders;

}
