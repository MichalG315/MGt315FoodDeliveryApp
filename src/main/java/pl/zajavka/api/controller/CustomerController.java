package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.RestaurantDeliveryAddressesService;
import pl.zajavka.business.RestaurantService;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    static final String CUSTOMER_PAGE = "/customerPage";

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantDeliveryAddressesService restaurantDeliveryAddressesService;

    @GetMapping(value = CUSTOMER_PAGE)
    public String getCustomerPagePaginatedAndSorted(
            @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(value = "sortField", defaultValue = "restaurantName") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
            @RequestParam(value = "streetName", defaultValue = "All") String streetName,
            @RequestParam(value = "city", defaultValue = "All") String city,
            Model model
    ) {
        Page<RestaurantDTO> restaurantDTOPage = restaurantService
                .findAvailable(pageNo, pageSize, sortField, sortDirection, streetName, city)
                .map(restaurantMapper::mapToDTO);

        List<RestaurantDTO> availableRestaurants = restaurantDTOPage.getContent();
        Set<String> cities = restaurantDeliveryAddressesService.findAvailableCities();
        Set<String> streetNames = restaurantDeliveryAddressesService.findAvailableStreetNames();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("streetName", streetName);
        model.addAttribute("city", city);
        model.addAttribute("totalPages", restaurantDTOPage.getTotalPages());
        model.addAttribute("totalItems", restaurantDTOPage.getTotalElements());
        model.addAttribute("availableRestaurantDTOs", availableRestaurants);
        model.addAttribute("citiesDTO", cities);
        model.addAttribute("streetNamesDTO", streetNames);

        return "customer_page";
    }

}
