package pl.zajavka.infrastructure.database.repository.util;

import pl.zajavka.api.controller.MenuItemCategories;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.Restaurant;

import java.math.BigDecimal;

public class DTOFixtures {
    public static MenuItemDTO someMenuItem1(Restaurant restaurant) {
        return MenuItemDTO.builder()
                .menuItemNumber("test1")
                .itemName("test1")
                .description("test1")
                .price(new BigDecimal(1))
                .category(MenuItemCategories.BREAKFAST.getToPrint())
                .restaurantName(restaurant.getRestaurantName())
                .build();
    }
}
