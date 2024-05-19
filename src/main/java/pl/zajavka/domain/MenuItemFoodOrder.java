package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

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
