package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.MenuItemService;
import pl.zajavka.business.RestaurantService;
import pl.zajavka.domain.MenuItem;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    static final String RESTAURANTS = "/order";
    static final String RESTAURANT_NAME = "/{restaurantName}";

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;

    @GetMapping(RESTAURANTS + RESTAURANT_NAME)
    public String showRestaurantPage(@PathVariable String restaurantName, Model model) {

        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurantService.findByRestaurantName(restaurantName));
        List<MenuItemDTO> menuItemsByRestaurantName =
                menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName).stream()
                        .map(menuItemMapper::map)
                        .toList();


        model.addAttribute("restaurantDTO", restaurantDTO);
        model.addAttribute("menuItemDTOs", menuItemsByRestaurantName);

        return "order";
    }


}
