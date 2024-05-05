package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.FoodOrderDAO;
import pl.zajavka.domain.Order;
import pl.zajavka.exception.NotFoundException;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;
import pl.zajavka.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.FoodOrderJpaRepository;
import pl.zajavka.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.FoodOrderEntityMapper;
import pl.zajavka.infrastructure.security.jpa.UserJpaRepository;

@Repository
@AllArgsConstructor
public class FoodOrderRepository implements FoodOrderDAO {

    private final FoodOrderJpaRepository foodOrderJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final MenuItemFoodOrderRepository menuItemFoodOrderRepository;


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
}
