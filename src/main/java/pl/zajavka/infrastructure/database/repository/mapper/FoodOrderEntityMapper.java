package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.Order;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodOrderEntityMapper {

    default FoodOrderEntity mapToEntity(Order order, CustomerEntity customerEntity, RestaurantEntity restaurantEntity) {
        return FoodOrderEntity.builder()
                .foodOrderNumber(order.getOrderNumber())
                .receivedDateTime(order.getReceivedDateTime())
                .totalAmount(order.getTotalAmount())
                .customer(customerEntity)
                .restaurant(restaurantEntity)
                .Status(order.getStatus())
                .build();
    }

    default Order mapFromEntity(FoodOrderEntity foodOrderEntity, String userName) {
        return Order.builder()
                .userName(userName)
                .restaurantName(foodOrderEntity.getRestaurant().getRestaurantName())
                .orderNumber(foodOrderEntity.getFoodOrderNumber())
                .receivedDateTime(foodOrderEntity.getReceivedDateTime())
                .completedDateTime(foodOrderEntity.getCompletedDateTime())
                .totalAmount(foodOrderEntity.getTotalAmount())
                .status(foodOrderEntity.getStatus())
                .build();
    }

    default Order mapFromEntity(FoodOrderEntity foodOrderEntity) {
        return Order.builder()
                .restaurantName(foodOrderEntity.getRestaurant().getRestaurantName())
                .orderNumber(foodOrderEntity.getFoodOrderNumber())
                .receivedDateTime(foodOrderEntity.getReceivedDateTime())
                .completedDateTime(foodOrderEntity.getCompletedDateTime())
                .totalAmount(foodOrderEntity.getTotalAmount())
                .build();
    }
}
