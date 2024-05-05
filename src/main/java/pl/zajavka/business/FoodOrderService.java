package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.FoodOrderDAO;
import pl.zajavka.domain.Order;

@Service
@AllArgsConstructor
public class FoodOrderService {

    private final FoodOrderDAO foodOrderDAO;

    public void saveFoodOrder(Order order, String restaurantName, String userName) {
        foodOrderDAO.saveFoodOrder(order, restaurantName,  userName);
    }
}
