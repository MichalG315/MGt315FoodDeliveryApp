package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.MenuItemDAO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.MenuItemEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class MenuItemRepository implements MenuItemDAO {

    private final MenuItemJpaRepository menuItemJpaRepository;
    private final MenuItemEntityMapper menuItemEntityMapper;

    @Override
    public List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName) {
        return menuItemJpaRepository.findAllByRestaurant_RestaurantName(restaurantName).stream()
                .map(menuItemEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public MenuItem saveMenuItem(MenuItem menuItem) {
        MenuItemEntity toSave = menuItemEntityMapper.mapToEntity(menuItem);
        MenuItemEntity saved = menuItemJpaRepository.save(toSave);
        return menuItemEntityMapper.mapFromEntity(saved);
    }

    @Override
    public void deleteAll() {
        menuItemJpaRepository.deleteAll();
    }


}
