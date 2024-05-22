package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T17:48:04+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class MenuItemMapperImpl implements MenuItemMapper {

    @Override
    public MenuItemDTO mapToDTO(MenuItem menuItem) {
        if ( menuItem == null ) {
            return null;
        }

        MenuItemDTO.MenuItemDTOBuilder menuItemDTO = MenuItemDTO.builder();

        menuItemDTO.menuItemNumber( menuItem.getMenuItemNumber() );
        menuItemDTO.itemName( menuItem.getItemName() );
        menuItemDTO.description( menuItem.getDescription() );
        menuItemDTO.price( menuItem.getPrice() );
        menuItemDTO.category( menuItem.getCategory() );
        menuItemDTO.restaurantName( menuItem.getRestaurantName() );
        menuItemDTO.imagePath( menuItem.getImagePath() );

        return menuItemDTO.build();
    }

    @Override
    public MenuItem mapFromDTO(MenuItemDTO menuItemDTO) {
        if ( menuItemDTO == null ) {
            return null;
        }

        MenuItem.MenuItemBuilder menuItem = MenuItem.builder();

        menuItem.menuItemNumber( menuItemDTO.getMenuItemNumber() );
        menuItem.itemName( menuItemDTO.getItemName() );
        menuItem.description( menuItemDTO.getDescription() );
        menuItem.price( menuItemDTO.getPrice() );
        menuItem.category( menuItemDTO.getCategory() );
        menuItem.imagePath( menuItemDTO.getImagePath() );
        menuItem.restaurantName( menuItemDTO.getRestaurantName() );

        return menuItem.build();
    }
}
