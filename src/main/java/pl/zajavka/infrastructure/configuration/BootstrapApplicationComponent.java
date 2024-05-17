package pl.zajavka.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.zajavka.business.MenuItemService;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.infrastructure.database.repository.FoodOrderRepository;
import pl.zajavka.infrastructure.database.repository.MenuItemFoodOrderRepository;
import pl.zajavka.infrastructure.database.repository.MenuItemRepository;

import java.math.BigDecimal;


@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

    private MenuItemRepository menuItemRepository;
    private MenuItemFoodOrderRepository menuItemFoodOrderRepository;
    private FoodOrderRepository foodOrderRepository;

    private MenuItemService menuItemService;

    @Override
    public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {
        menuItemFoodOrderRepository.deleteAll();
        foodOrderRepository.deleteAll();
        menuItemRepository.deleteAll();

        String restaurantName = "Pizza center";

        menuItemRepository.saveMenuItem(MenuItem.builder()
                .itemName("Everyday pizza")
                .menuItemNumber(menuItemService.assignNumber(restaurantName))
                .description("""
                        Pizza with tomato sauce, mozzarella and ham.
                        """)
                .price(new BigDecimal(20))
                .category("Pizza")
                .imagePath("/images/foodImages/everyday_pizza.png")
                .build(), restaurantName);

        menuItemRepository.saveMenuItem(MenuItem.builder()
                .itemName("Pepperoni pizza")
                .menuItemNumber(menuItemService.assignNumber(restaurantName))
                .description("""
                        Pizza with tomato sauce, mozzarella and pepperoni.
                        """)
                .price(new BigDecimal(25))
                .category("Pizza")
                .imagePath("/images/foodImages/pepperoni_pizza.png")
                .build(), restaurantName);

        menuItemRepository.saveMenuItem(MenuItem.builder()
                .itemName("Vegetariana pizza")
                .menuItemNumber(menuItemService.assignNumber(restaurantName))
                .description("""
                        Pizza with tomato sauce, mozzarella, corn, pepper and onion.
                        """)
                .price(new BigDecimal(23))
                .category("Pizza")
                .imagePath("/images/foodImages/vegetariana_pizza.png")
                .build(), restaurantName);
    }
}
