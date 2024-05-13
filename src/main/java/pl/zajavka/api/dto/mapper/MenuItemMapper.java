package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.domain.MenuItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuItemMapper {


    MenuItemDTO mapToDTO(MenuItem menuItem);

    MenuItem mapFromDTO(MenuItemDTO menuItemDTO);

}
