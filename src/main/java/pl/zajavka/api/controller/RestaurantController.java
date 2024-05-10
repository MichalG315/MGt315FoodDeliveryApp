package pl.zajavka.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.AddressMapper;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class RestaurantController {
    static final String RESTAURANT_PAGE = "/restaurantPage";
    static final String RESTAURANT_USER_NAME = "/{restaurantUserName}";
    static final String ORDERS = "/orders";
    static final String MENU = "/menu";
    static final String ORDER_NUMBER = "/{orderNumber}";
    static final String DELIVERED = "/delivered";
    static final String ADD = "/add";
    static final String ADDRESS = "/address";

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final FoodOrderService foodOrderService;
    private final ImageService imageService;
    private final RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;

    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;

    @GetMapping(value = RESTAURANT_PAGE)
    public String getRestaurantPage() {
        return "restaurant_page";
    }

    @GetMapping(value = RESTAURANT_PAGE + MENU + RESTAURANT_USER_NAME)
    public String getMenuItemsPage(
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

        return "restaurant_menu_items";
    }

    @GetMapping(value = RESTAURANT_PAGE + ORDERS + RESTAURANT_USER_NAME)
    public String getYourOrders(
            @PathVariable String restaurantUserName,
            Model model
    ) {
        List<OrderDTO> availableRestaurantOrders = foodOrderService.availableFoodOrdersByRestaurantName(restaurantUserName)
                .stream().map(orderMapper::mapToDTO)
                .toList();

        model.addAttribute("availableCustomerOrderDTOs", availableRestaurantOrders);

        return "restaurant_your_orders";
    }

    @PatchMapping(value = RESTAURANT_PAGE + ORDERS + DELIVERED + RESTAURANT_USER_NAME + ORDER_NUMBER)
    public String patchOrderCompletedDateTime(
            @PathVariable String restaurantUserName,
            @PathVariable String orderNumber
    ) {
        foodOrderService.setCompletedDateTime(orderNumber);

        return "redirect:" + RESTAURANT_PAGE + ORDERS + "/" + restaurantUserName;
    }

    @GetMapping(value = RESTAURANT_PAGE + ADD)
    public ModelAndView getAddMenuItem() {

        Map<String, ?> model = Map.of("menuItemDTO", new MenuItemDTO(),
                "CategoriesDTO", Arrays.stream(MenuItemCategories.values())
                        .map(MenuItemCategories::getToPrint).toList());


        return new ModelAndView("restaurant_add_menu_item", model);
    }

    @PostMapping(value = RESTAURANT_PAGE + ADD + RESTAURANT_USER_NAME)
    public String addMenuItem(
            @PathVariable String restaurantUserName,
            @Valid @ModelAttribute("menuItemDTO") MenuItemDTO menuItemDTO,
            @RequestParam("image") MultipartFile file
    ) {
        String imagePath = imageService.saveImage(file);
        menuItemDTO.setImagePath(imagePath);
        MenuItem menuItem = menuItemMapper.mapFromDTO(menuItemDTO);
        menuItemService.saveNewMenuItem(menuItem, restaurantUserName);

        return "redirect:/";
    }

    @GetMapping(value = RESTAURANT_PAGE + ADDRESS + RESTAURANT_USER_NAME)
    public String getAddAddress(
            @PathVariable String restaurantUserName,
            Model model
    ) {
        List<AddressDTO> deliveryAddresses = restaurantDeliveryAddressesService
                .findDeliveryAddresses(restaurantUserName).stream()
                .map(addressMapper::mapToDTO)
                .toList();

        model.addAttribute("addressDTO", new AddressDTO());
        model.addAttribute("deliveryAddresses", deliveryAddresses);

        return "restaurant_add_delivery_address";
    }

    @PostMapping(value = RESTAURANT_PAGE + ADDRESS + RESTAURANT_USER_NAME + ADD)
    public String deliveryAddressAdded(
            @PathVariable String restaurantUserName,
            @Valid @ModelAttribute("addressDTO") AddressDTO addressDTO
    ) {
        Address address = addressMapper.mapFromDTO(addressDTO);
        restaurantDeliveryAddressesService.saveNewDeliveryAddress(restaurantUserName, address);
        return "redirect:/restaurantPage/address/" + restaurantUserName;
    }
}
