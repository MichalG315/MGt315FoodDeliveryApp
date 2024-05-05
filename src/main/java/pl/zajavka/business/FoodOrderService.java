package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.FoodOrderDAO;
import pl.zajavka.domain.Order;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodOrderService {

    private final FoodOrderDAO foodOrderDAO;

    public List<Order> availableFoodOrdersByUserName(String userName) {
        return foodOrderDAO.availableFoodOrdersByUserName(userName);
    }

    public void saveFoodOrder(Order order, String restaurantName, String userName) {
        foodOrderDAO.saveFoodOrder(order, restaurantName, userName);
    }

    public void deleteFoodOrder(String orderNumber) {
        foodOrderDAO.deleteOrder(orderNumber);
    }


    public OffsetDateTime findFoodOrderReceivedDateTime(String orderNumber) {
        return findFoodOrder(orderNumber).getReceivedDateTime();
    }

    public Order findFoodOrder(String orderNumber) {
        return foodOrderDAO.findFoodOrder(orderNumber);
    }
}
