package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {


    MenuItemDTO mapToDTO(MenuItem menuItem);

    MenuItem mapFromDTO(MenuItemDTO menuItemDTO);

}
