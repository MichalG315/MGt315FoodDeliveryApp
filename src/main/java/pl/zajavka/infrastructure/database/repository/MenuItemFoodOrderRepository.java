package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.MenuItemFoodOrderDAO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.Order;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemFoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.MenuItemFoodOrderEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class MenuItemFoodOrderRepository implements MenuItemFoodOrderDAO {

    private final MenuItemFoodOrderJpaRepository menuItemFoodOrderJpaRepository;
    private final MenuItemJpaRepository menuItemJpaRepository;

    private final MenuItemFoodOrderEntityMapper menuItemFoodOrderEntityMapper;

    @Override
    public void saveMenuItemFoodOrder(Order order, FoodOrderEntity savedfoodOrderEntity) {
        List<MenuItemFoodOrderEntity> toSaveList = new ArrayList<>();

        Map<MenuItem, Long> menuItemMap = order.getMenuItemMap();


        for (Map.Entry<MenuItem, Long> entry : menuItemMap.entrySet()) {
            String menuItemNumber = entry.getKey().getMenuItemNumber();
            toSaveList.add(
                    MenuItemFoodOrderEntity.builder()
                            .quantity(Integer.parseInt(entry.getValue().toString()))
                            .menuItem(menuItemJpaRepository.findByMenuItemNumber(menuItemNumber)
                                    .orElseThrow(() ->
                                            new NotFoundException("Could not find menu item with number: %s"
                                                    .formatted(menuItemNumber)))
                            )
                            .foodOrder(savedfoodOrderEntity)
                            .build()
            );
        }
        menuItemFoodOrderJpaRepository.saveAllAndFlush(toSaveList);
    }
}
