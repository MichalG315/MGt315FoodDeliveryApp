package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.business.MenuItemService;
import pl.zajavka.business.RestaurantService;
import pl.zajavka.business.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class RestaurantController {
    static final String RESTAURANT_PAGE = "/restaurantPage";
    static final String RESTAURANT_NAME = "/{restaurantUserName}";
    static final String ORDERS = "/orders";

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    private final MenuItemMapper menuItemMapper;

    @GetMapping(value = RESTAURANT_PAGE)
    public String getRestaurantPage() {
        return "restaurant_page";
    }


    @GetMapping(value = RESTAURANT_PAGE + ORDERS + RESTAURANT_NAME)
    public String menuItemsPage(
            @PathVariable String restaurantUserName,
            Model model
    ) {
        Integer userId = userService.findUserId(restaurantUserName);
        String restaurantName = restaurantService.findRestaurantNameByUserId(userId);

        List<MenuItemDTO> menuItemsByRestaurantName =
                menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName).stream()
                        .map(menuItemMapper::mapToDTO)
                        .toList();

        model.addAttribute("menuItemDTOs", menuItemsByRestaurantName);

        return "restaurant_orders";
    }


}
