package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {


    MenuItemDTO map(MenuItem menuItem);
}