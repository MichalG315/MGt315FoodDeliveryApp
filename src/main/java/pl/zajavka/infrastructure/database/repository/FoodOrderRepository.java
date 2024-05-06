package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.FoodOrderDAO;
import pl.zajavka.domain.Order;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.FoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.MenuItemFoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.FoodOrderEntityMapper;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class FoodOrderRepository implements FoodOrderDAO {

    private final FoodOrderJpaRepository foodOrderJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final MenuItemFoodOrderRepository menuItemFoodOrderRepository;
    private final MenuItemFoodOrderJpaRepository menuItemFoodOrderJpaRepository;


    private final FoodOrderEntityMapper foodOrderEntityMapper;

    @Override
    public void saveFoodOrder(Order order, String restaurantName, String userName) {
        String customerEmail = userJpaRepository.findByUserName(userName).getEmail();
        CustomerEntity customerEntity = customerJpaRepository.findByEmail(customerEmail)
                .orElseThrow(() ->
                        new NotFoundException("Could not find customer with email: %s".formatted(customerEmail)));
        RestaurantEntity restaurantEntity = restaurantJpaRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() ->
                        new NotFoundException("Could not find restaurant named: %s".formatted(restaurantName)));

        FoodOrderEntity foodOrderEntity = foodOrderEntityMapper.mapToEntity(order, customerEntity, restaurantEntity);
        FoodOrderEntity savedfoodOrderEntity = foodOrderJpaRepository.saveAndFlush(foodOrderEntity);

        menuItemFoodOrderRepository.saveMenuItemFoodOrder(order, savedfoodOrderEntity);


    }

    @Override
    public void deleteAll() {
        foodOrderJpaRepository.deleteAll();
    }

    @Override
    public List<Order> availableFoodOrdersByUserName(String userName) {
        String customerEmail = userJpaRepository.findByUserName(userName).getEmail();
        CustomerEntity customer = customerJpaRepository.findByEmail(customerEmail)
                .orElseThrow(() ->
                        new NotFoundException("Could not find customer with email: %s".formatted(customerEmail)));

        return foodOrderJpaRepository.findAllByCustomer(customer).stream()
                .map(foodOrderEntity -> foodOrderEntityMapper.mapFromEntity(foodOrderEntity, userName))
                .toList();

    }

    @Override
    public List<Order> availableFoodOrdersByRestaurantName(String restaurantUserName) {
        int restaurantUserId = userJpaRepository.findByUserName(restaurantUserName).getId();
        RestaurantEntity restaurant = restaurantJpaRepository.findByUserId(restaurantUserId);
        return foodOrderJpaRepository.findAllByRestaurant(restaurant).stream()
                .map(foodOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void deleteOrder(String orderNumber) {
        FoodOrderEntity foodOrderEntity = foodOrderJpaRepository.findByFoodOrderNumber(orderNumber);
        List<MenuItemFoodOrderEntity> menuItemFoodOrderEntities = foodOrderEntity.getMenuItemFoodOrders()
                .stream().toList();
        menuItemFoodOrderJpaRepository.deleteAll(menuItemFoodOrderEntities);
        foodOrderJpaRepository.delete(foodOrderEntity);
    }


    @Override
    public Order findFoodOrder(String orderNumber) {
        return foodOrderEntityMapper.mapFromEntity(foodOrderJpaRepository.findByFoodOrderNumber(orderNumber));
    }

    @Override
    public void setCompletedDateTime(String orderNumber, OffsetDateTime completedDateTime) {
        FoodOrderEntity foodOrderEntity = foodOrderJpaRepository.findByFoodOrderNumber(orderNumber);
        foodOrderEntity.setCompletedDateTime(completedDateTime);
        foodOrderEntity.setStatus("Delivered");
        foodOrderJpaRepository.saveAndFlush(foodOrderEntity);
    }
}
