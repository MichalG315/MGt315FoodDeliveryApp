package pl.zajavka.infrastructure.database.repository.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.MenuItemFoodOrder;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T11:08:20+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class MenuItemEntityMapperImpl implements MenuItemEntityMapper {

    @Override
    public MenuItem mapFromEntity(MenuItemEntity menuItemEntity) {
        if ( menuItemEntity == null ) {
            return null;
        }

        MenuItem.MenuItemBuilder menuItem = MenuItem.builder();

        menuItem.restaurantName( menuItemEntityRestaurantRestaurantName( menuItemEntity ) );
        menuItem.menuItemId( menuItemEntity.getMenuItemId() );
        menuItem.menuItemNumber( menuItemEntity.getMenuItemNumber() );
        menuItem.itemName( menuItemEntity.getItemName() );
        menuItem.description( menuItemEntity.getDescription() );
        menuItem.price( menuItemEntity.getPrice() );
        menuItem.category( menuItemEntity.getCategory() );
        menuItem.imagePath( menuItemEntity.getImagePath() );

        return menuItem.build();
    }

    @Override
    public MenuItemEntity mapToEntity(MenuItem menuItem) {
        if ( menuItem == null ) {
            return null;
        }

        MenuItemEntity.MenuItemEntityBuilder menuItemEntity = MenuItemEntity.builder();

        menuItemEntity.menuItemId( menuItem.getMenuItemId() );
        menuItemEntity.menuItemNumber( menuItem.getMenuItemNumber() );
        menuItemEntity.itemName( menuItem.getItemName() );
        menuItemEntity.description( menuItem.getDescription() );
        menuItemEntity.price( menuItem.getPrice() );
        menuItemEntity.category( menuItem.getCategory() );
        menuItemEntity.imagePath( menuItem.getImagePath() );
        menuItemEntity.menuItemFoodOrders( menuItemFoodOrderSetToMenuItemFoodOrderEntitySet( menuItem.getMenuItemFoodOrders() ) );

        return menuItemEntity.build();
    }

    private String menuItemEntityRestaurantRestaurantName(MenuItemEntity menuItemEntity) {
        if ( menuItemEntity == null ) {
            return null;
        }
        RestaurantEntity restaurant = menuItemEntity.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        String restaurantName = restaurant.getRestaurantName();
        if ( restaurantName == null ) {
            return null;
        }
        return restaurantName;
    }

    protected MenuItemFoodOrderEntity menuItemFoodOrderToMenuItemFoodOrderEntity(MenuItemFoodOrder menuItemFoodOrder) {
        if ( menuItemFoodOrder == null ) {
            return null;
        }

        MenuItemFoodOrderEntity.MenuItemFoodOrderEntityBuilder menuItemFoodOrderEntity = MenuItemFoodOrderEntity.builder();

        menuItemFoodOrderEntity.menuItemFoodOrderId( menuItemFoodOrder.getMenuItemFoodOrderId() );
        menuItemFoodOrderEntity.quantity( menuItemFoodOrder.getQuantity() );
        menuItemFoodOrderEntity.menuItem( mapToEntity( menuItemFoodOrder.getMenuItem() ) );

        return menuItemFoodOrderEntity.build();
    }

    protected Set<MenuItemFoodOrderEntity> menuItemFoodOrderSetToMenuItemFoodOrderEntitySet(Set<MenuItemFoodOrder> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuItemFoodOrderEntity> set1 = new LinkedHashSet<MenuItemFoodOrderEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuItemFoodOrder menuItemFoodOrder : set ) {
            set1.add( menuItemFoodOrderToMenuItemFoodOrderEntity( menuItemFoodOrder ) );
        }

        return set1;
    }
}
