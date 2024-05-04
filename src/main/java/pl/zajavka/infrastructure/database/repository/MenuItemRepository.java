package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.MenuItemDAO;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.MenuItemEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MenuItemRepository implements MenuItemDAO {

    private final MenuItemJpaRepository menuItemJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;

    private final MenuItemEntityMapper menuItemEntityMapper;

    @Override
    public List<MenuItem> findAvailableMenuItemsByRestaurantName(String restaurantName) {
        return menuItemJpaRepository.findAllByRestaurant_RestaurantName(restaurantName).stream()
                .map(menuItemEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void saveMenuItem(MenuItem menuItem, String restaurantName) {
        MenuItemEntity toSave = menuItemEntityMapper.mapToEntity(menuItem);
        toSave.setRestaurant(restaurantJpaRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() ->
                        new NotFoundException("Could not find restaurant named: %s" .formatted(restaurantName))
                ));
        menuItemJpaRepository.save(toSave);
    }

    @Override
    public void deleteAll() {
        menuItemJpaRepository.deleteAll();
    }

    @Override
    public Optional<MenuItem> findMenuItemByMenuItemNumber(String menuItemNumber) {
        return menuItemJpaRepository.findByMenuItemNumber(menuItemNumber).map(
                menuItemEntityMapper::mapFromEntity);
    }


}
