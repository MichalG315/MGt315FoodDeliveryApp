package pl.zajavka.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.zajavka.api.dto.RestaurantDTO;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.business.RestaurantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(CustomerRestController.API_CUSTOMER_PAGE)
public class CustomerRestController {

    static final String API_CUSTOMER_PAGE = "/api/customerPage";

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping()
    public List<RestaurantDTO> getListOfRestaurant(
            @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(value = "sortField", defaultValue = "restaurantName") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
            @RequestParam(value = "streetName", defaultValue = "All") String streetName,
            @RequestParam(value = "city", defaultValue = "All") String city

    ) {
        Page<RestaurantDTO> restaurantDTOPage = restaurantService
                .findAvailable(pageNo, pageSize, sortField, sortDirection, streetName, city)
                .map(restaurantMapper::mapToDTO);

        return restaurantDTOPage.getContent();
    }

}
