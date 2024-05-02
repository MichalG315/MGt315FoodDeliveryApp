package pl.zajavka.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "menuItemNumber")
@ToString(of = {"menuItemId", "itemName", "description", "price", "category"})
public class MenuItem {

    Integer menuItemId;
    String menuItemNumber;
    String itemName;
    String description;
    BigDecimal price;
    String category;
    String imagePath;
    Restaurant restaurant;
    Set<MenuItemFoodOrder> menuItemFoodOrders;
}
