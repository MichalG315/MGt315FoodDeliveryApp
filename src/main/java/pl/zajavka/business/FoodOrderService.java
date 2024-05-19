package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.dao.FoodOrderDAO;
import pl.zajavka.domain.Order;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodOrderService {

    private final FoodOrderDAO foodOrderDAO;

    @Transactional
    public List<Order> availableFoodOrdersByUserName(String userName) {
        return foodOrderDAO.availableFoodOrdersByUserName(userName);
    }

    @Transactional
    public List<Order> availableFoodOrdersByRestaurantName(String restaurantUserName) {
        return foodOrderDAO.availableFoodOrdersByRestaurantName(restaurantUserName);
    }

    @Transactional
    public void saveFoodOrder(Order order, String restaurantName, String userName) {
        foodOrderDAO.saveFoodOrder(order, restaurantName, userName);
    }

    @Transactional
    public void deleteFoodOrder(String orderNumber) {
        foodOrderDAO.deleteOrder(orderNumber);
    }

    @Transactional
    public OffsetDateTime findFoodOrderReceivedDateTime(String orderNumber) {
        return findFoodOrder(orderNumber).getReceivedDateTime();
    }

    @Transactional
    public void setCompletedDateTime(String orderNumber) {
        OffsetDateTime completedDateTime = OffsetDateTime.now();
        foodOrderDAO.setCompletedDateTime(orderNumber, completedDateTime);
    }

    private Order findFoodOrder(String orderNumber) {
        return foodOrderDAO.findFoodOrder(orderNumber);
    }
}
