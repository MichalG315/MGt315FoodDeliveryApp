package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.Order;
import pl.zajavka.domain.Restaurant;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final FoodOrderService foodOrderService;

    private final MenuItemMapper menuItemMapper;

    public void buildAndSaveOrder(String restaurantName, String userName, List<MenuItemDTO> cart) {
        Order order = Order.builder()
                .userName(userName)
                .restaurantName(restaurantName)
                .orderNumber(generateOrderNumber(userName))
                .menuItemMap(menuItemListToMenuItemMap(cart))
                .receivedDateTime(OffsetDateTime.now())
                .totalAmount(calculateTotalAmount(cart))
                .status("In progress")
                .build();

        foodOrderService.saveFoodOrder(order, restaurantName, userName);
    }

    private Map<MenuItem, Long> menuItemListToMenuItemMap(List<MenuItemDTO> list) {
        return list.stream()
                .map(menuItemMapper::mapFromDTO)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
    }

    private BigDecimal calculateTotalAmount(List<MenuItemDTO> cart) {
        return cart.stream()
                .map(MenuItemDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String generateOrderNumber(String userName) {
        return userName + "-" + RandomStringUtils.randomAlphanumeric(10);
    }
}
