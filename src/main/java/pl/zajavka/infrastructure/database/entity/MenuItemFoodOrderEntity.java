package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "menuItemFoodOrderId")
@ToString(of = {"menuItemFoodOrderId", "quantity"})
@Getter
@Setter
@Table(name = "menu_item_food_order")
public class MenuItemFoodOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_food_order_id")
    private Integer menuItemFoodOrderId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_order_id")
    private FoodOrderEntity foodOrder;
}
