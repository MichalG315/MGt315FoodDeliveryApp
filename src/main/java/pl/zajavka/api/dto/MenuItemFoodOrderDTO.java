package pl.zajavka.api.dto;

import lombok.*;
import pl.zajavka.domain.FoodOrder;
import pl.zajavka.domain.MenuItem;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemFoodOrderDTO {

    private Integer quantity;
    private MenuItem menuItem;
    private FoodOrder foodOrder;
}
