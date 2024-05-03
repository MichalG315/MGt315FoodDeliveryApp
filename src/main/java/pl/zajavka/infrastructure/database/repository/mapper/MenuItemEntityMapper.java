package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuItemEntityMapper {

    @Mapping(target = "menuItemFoodOrders", ignore = true)
    @Mapping(target = "restaurant.addressExtended", ignore = true)
    @Mapping(target = "restaurant.restaurantDeliveryAddresses", ignore = true)
    @Mapping(target = "restaurant.menuItems", ignore = true)
    @Mapping(target = "restaurant.foodOrders", ignore = true)
    MenuItem mapFromEntity(MenuItemEntity menuItemEntity);

    MenuItemEntity mapToEntity(MenuItem menuItem);
}
