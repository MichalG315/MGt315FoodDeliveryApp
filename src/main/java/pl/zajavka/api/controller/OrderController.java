package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.MenuItemService;
import pl.zajavka.business.OrderService;
import pl.zajavka.business.RestaurantService;
import pl.zajavka.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    static final String ORDER = "/order";
    static final String SUBMIT = "/submit";
    static final String RESTAURANT_NAME = "/{restaurantName}";
    static final String MENU_ITEM_NUMBER = "/{menuItemNumber}";
    static final String USER_NAME = "/{userName}";

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final OrderService orderService;

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;

    private final List<MenuItemDTO> cart = new ArrayList<>();

    @GetMapping(ORDER + RESTAURANT_NAME)
    public String showRestaurantPage(@PathVariable String restaurantName, Model model) {

        RestaurantDTO restaurantDTO = restaurantMapper.mapToDTO(restaurantService.findByRestaurantName(restaurantName));

        List<MenuItemDTO> menuItemsByRestaurantName =
                menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName).stream()
                        .map(menuItemMapper::mapToDTO)
                        .toList();


        model.addAttribute("restaurantDTO", restaurantDTO);
        model.addAttribute("menuItemDTOs", menuItemsByRestaurantName);
        model.addAttribute("orderDTOs", cart);

        return "order";
    }

    @PostMapping(ORDER + RESTAURANT_NAME + MENU_ITEM_NUMBER)
    public String addToCart(
            @PathVariable String restaurantName,
            @PathVariable String menuItemNumber
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        cart.add(menuItemDTO);

        return "redirect:/order/" + restaurantName;
    }

    @DeleteMapping(ORDER + RESTAURANT_NAME + MENU_ITEM_NUMBER)
    public String deleteFromCart(
            @PathVariable String restaurantName,
            @PathVariable String menuItemNumber
    ) {
        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemService.findMenuItemByMenuItemNumber(menuItemNumber));
        cart.remove(menuItemDTO);

        return "redirect:/order/" + restaurantName;
    }

    @PostMapping(ORDER + RESTAURANT_NAME + SUBMIT + USER_NAME)
    public String submitOrder(
            @PathVariable String restaurantName,
            @PathVariable String userName) {

        Order order = orderService.buildAndSaveOrder(restaurantName, userName, cart);
        cart.clear();
        return "redirect:/";
    }


}
