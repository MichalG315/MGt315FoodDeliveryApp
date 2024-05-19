package pl.zajavka.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.api.dto.AddressDTO;
import pl.zajavka.api.dto.MenuItemDTO;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.api.dto.mapper.AddressMapper;
import pl.zajavka.api.dto.mapper.MenuItemMapper;
import pl.zajavka.api.dto.mapper.OrderMapper;
import pl.zajavka.business.*;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.MenuItem;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(RestaurantRestController.API_RESTAURANT_PAGE)
public class RestaurantRestController {

    public static final String API_RESTAURANT_PAGE = "/api/restaurantPage";

    public static final String RESTAURANT_USER_NAME = "/{restaurantUserName}";
    public static final String MENU = "/menu";
    public static final String ORDERS = "/orders";
    public static final String ORDER_NUMBER = "/{orderNumber}";
    public static final String DELIVERED = "/delivered";
    public static final String ADD = "/add";
    public static final String ADDRESS = "/address";

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final FoodOrderService foodOrderService;
    private final ImageService imageService;
    private final RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;

    private final MenuItemMapper menuItemMapper;
    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;

    @GetMapping(value = MENU + RESTAURANT_USER_NAME)
    public List<MenuItemDTO> getRestaurantMenuItems(
            @PathVariable String restaurantUserName
    ) {
        Integer userId = userService.findUserId(restaurantUserName);
        String restaurantName = restaurantService.findRestaurantNameByUserId(userId);

        return menuItemService.findAvailableMenuItemsByRestaurantName(restaurantName).stream()
                .map(menuItemMapper::mapToDTO)
                .toList();
    }

    @GetMapping(value = ORDERS + RESTAURANT_USER_NAME)
    public List<OrderDTO> getYourOrders(
            @PathVariable String restaurantUserName
    ) {
        return foodOrderService.availableFoodOrdersByRestaurantName(restaurantUserName)
                .stream().map(orderMapper::mapToDTO)
                .toList();
    }

    @PatchMapping(value = ORDERS + DELIVERED + RESTAURANT_USER_NAME + ORDER_NUMBER)
    public String patchOrderCompletedDateTime(
            @PathVariable String orderNumber
    ) {
        foodOrderService.setCompletedDateTime(orderNumber);
        return "Delivery time has been set";
    }

    @PostMapping(value = ADD + RESTAURANT_USER_NAME, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addMenuItem(
            @PathVariable String restaurantUserName,
            @Valid @ModelAttribute("menuItemDTO") MenuItemDTO menuItemDTO,
            @RequestParam("image") MultipartFile file
    ) {
        String imagePath = imageService.saveImage(file);
        menuItemDTO.setImagePath(imagePath);
        MenuItem menuItem = menuItemMapper.mapFromDTO(menuItemDTO);
        menuItemService.saveNewMenuItem(menuItem, restaurantUserName);

        return "Menu item added";
    }

    @PostMapping(value = ADDRESS + RESTAURANT_USER_NAME + ADD)
    public String deliveryAddressAdded(
            @PathVariable String restaurantUserName,
            @Valid @ModelAttribute("addressDTO") AddressDTO addressDTO
    ) {
        Address address = addressMapper.mapFromDTO(addressDTO);
        restaurantDeliveryAddressesService.saveNewDeliveryAddress(restaurantUserName, address);

        return "Delivery address added";
    }
}
