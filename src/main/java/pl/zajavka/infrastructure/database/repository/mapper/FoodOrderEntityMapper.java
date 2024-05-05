package pl.zajavka.infrastructure.database.repository.mapper;

import jakarta.persistence.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.Order;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodOrderEntityMapper {

    default FoodOrderEntity mapToEntity(Order order, CustomerEntity customerEntity, RestaurantEntity restaurantEntity) {
        return FoodOrderEntity.builder()
                .foodOrderNumber(order.getOrderNumber())
                .receivedDateTime(order.getReceivedDateTime())
                .totalAmount(order.getTotalAmount())
                .customer(customerEntity)
                .restaurant(restaurantEntity)
                .build();
    }
}
