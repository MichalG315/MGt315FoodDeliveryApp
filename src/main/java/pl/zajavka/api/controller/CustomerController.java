package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.OrderService;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    static final String RESTAURANTS = "/customerPage";

    private final OrderService orderService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping(value = RESTAURANTS)
    public String getOrder(Model model) {
        var availableRestaurants = orderService.availableRestaurants().stream()
                .map(restaurantMapper::map)
                .toList();

        model.addAttribute("availableRestaurantDTOs", availableRestaurants);


        return "customer_page";
    }
}
