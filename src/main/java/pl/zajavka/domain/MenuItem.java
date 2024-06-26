package pl.zajavka.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(of = "menuItemNumber")
@ToString(of = {"menuItemId", "itemName", "description", "price", "category"})
public class MenuItem {
    Integer menuItemId;
    @With
    String menuItemNumber;
    String itemName;
    String description;
    BigDecimal price;
    String category;
    String imagePath;
    String restaurantName;
    Set<MenuItemFoodOrder> menuItemFoodOrders;
}
