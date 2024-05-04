package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T23:34:43+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class MenuItemMapperImpl implements MenuItemMapper {

    @Override
    public MenuItemDTO map(MenuItem menuItem) {
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

        return menuItemDTO.build();
    }
}
