package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.api.dto.OrderDTO;

@Service
@AllArgsConstructor
public class FoodOrderService {
    public void saveFoodOrder(OrderDTO orderDTO, String restaurantName, String userName) {

    }
}
