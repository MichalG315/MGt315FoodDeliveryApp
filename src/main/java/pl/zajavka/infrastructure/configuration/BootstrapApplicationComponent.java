package pl.zajavka.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.zajavka.business.MenuItemService;
import pl.zajavka.business.RestaurantService;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.infrastructure.database.repository.MenuItemRepository;
import pl.zajavka.infrastructure.security.repository.UserRepository;

import java.math.BigDecimal;


@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

    MenuItemRepository menuItemRepository;
    UserRepository userRepository;

    MenuItemService menuItemService;
    RestaurantService restaurantService;

    @Override
    public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {

        menuItemRepository.deleteAll();

        String restaurantName = "Chicken";

        menuItemRepository.saveMenuItem(MenuItem.builder()
                .itemName("Chicken nuggets")
                .menuItemNumber(menuItemService.assignNumber(restaurantName))
                .description("""
                        A chicken nugget is a food product consisting of a small piece of deboned chicken meat that
                        is breaded or battered, then deep-fried or baked.
                        """)
                .price(new BigDecimal(10))
                .category("Meat")
                .build(), restaurantName);

        menuItemRepository.saveMenuItem(MenuItem.builder()
                .itemName("Chicken & Broccoli")
                .menuItemNumber(menuItemService.assignNumber(restaurantName))
                .description("""
                        This fast and easy one-pot chicken and broccoli recipe is coated in a sweet and savory
                        sauce with plenty of ginger and garlic for a weeknight dinner that beats take-out.
                        This dish comes together in 20 minutes with minimal prep work too. Win win!
                        """)
                .price(new BigDecimal(12))
                .category("Meat")
                .build(), restaurantName);

        menuItemRepository.saveMenuItem(MenuItem.builder()
                .itemName("Chicken, Spinach, & Artichoke Lasagna")
                .menuItemNumber(menuItemService.assignNumber(restaurantName))
                .description("""
                        We love spinach–artichoke anything—the cheesy and veggie-packed combo is always a hit.
                        While we’ve already paired these flavors with gnocchi, tortellini, pasta shells, and more,
                        making a super-cheesy chicken, spinach, and artichoke lasagna just had to happen.
                        """)
                .price(new BigDecimal(15))
                .category("Meat")
                .build(), restaurantName);
    }
}
