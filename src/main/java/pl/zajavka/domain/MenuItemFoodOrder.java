package pl.zajavka.domain;

import lombok.*;

@Value
@Builder
@EqualsAndHashCode(of = {"menuItemFoodOrderId", "quantity", "menuItem"})
@ToString(of = {"menuItemFoodOrderId", "quantity"})
public class MenuItemFoodOrder {
    Integer menuItemFoodOrderId;
    Integer quantity;
    MenuItem menuItem;
    Order Order;
}
