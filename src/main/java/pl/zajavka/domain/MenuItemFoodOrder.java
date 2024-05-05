package pl.zajavka.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"menuItemFoodOrderId", "quantity", "menuItem"})
@ToString(of = {"menuItemFoodOrderId", "quantity"})
public class MenuItemFoodOrder {

    Integer menuItemFoodOrderId;
    Integer quantity;
    MenuItem menuItem;
    FoodOrder foodOrder;
}
