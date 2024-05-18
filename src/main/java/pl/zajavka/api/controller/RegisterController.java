package pl.zajavka.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.UserRestaurantDTO;
import pl.zajavka.api.dto.mapper.CustomerMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.api.dto.mapper.UserMapper;
import pl.zajavka.business.UserService;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    static final String REGISTER_CUSTOMER = "/register/customer";
    static final String REGISTER_CUSTOMER_DONE = "/register/customer/save";
    static final String REGISTER_RESTAURANT = "/register/restaurant";
    static final String REGISTER_RESTAURANT_DONE = "/register/restaurant/save";

    private final UserMapper userMapper;
    private final UserService userService;

    private final CustomerMapper customerMapper;
    private final RestaurantMapper restaurantMapper;

    @GetMapping(value = REGISTER_CUSTOMER)
    public ModelAndView registerCustomerPage() {

        Map<String, ?> model = Map.of("userCustomerDTO", UserCustomerDTO.buildDefault());

        return new ModelAndView("register_customer_page", model);
    }

    @SuppressWarnings("UnusedReturnValue")
    @PostMapping(REGISTER_CUSTOMER_DONE)
    public String successfulCustomerRegistration(
            @Valid @ModelAttribute("userCustomerDTO") UserCustomerDTO userCustomerDTO,
            Model model
    ) {
        String newUserName = userService.checkIfUserNameExists(userCustomerDTO.getUserName());
        String newEmail = userService.checkIfEmailExists(userCustomerDTO.getEmail());
        if (!Objects.isNull(newUserName)) {
            model.addAttribute("username", newUserName);
            return "error_invalid_register.html";
        } else if (!Objects.isNull(newEmail)) {
            model.addAttribute("email", newEmail);
            return "error_invalid_register.html";
        } else {
            User user = userMapper.map(userCustomerDTO).withRole(1);
            Customer customer = customerMapper.map(userCustomerDTO);
            userService.saveUser(user, customer);
            return "redirect:/";
        }
    }

    @GetMapping(value = REGISTER_RESTAURANT)
    public ModelAndView registerRestaurantPage() {

        Map<String, ?> model = Map.of("userRestaurantDTO", UserRestaurantDTO.buildDefault());

        return new ModelAndView("register_restaurant_page", model);
    }

    @SuppressWarnings("UnusedReturnValue")
    @PostMapping(REGISTER_RESTAURANT_DONE)
    public String successfulRestaurantRegistration(
            @Valid @ModelAttribute("userRestaurantDTO") UserRestaurantDTO userRestaurantDTO,
            Model model
    ) {

        String newUserName = userService.checkIfUserNameExists(userRestaurantDTO.getUserName());
        String newEmail = userService.checkIfEmailExists(userRestaurantDTO.getEmail());
        if (!Objects.isNull(newUserName)) {
            model.addAttribute("username", newUserName);
            return "error_invalid_register.html";
        } else if (!Objects.isNull(newEmail)) {
            model.addAttribute("email", newEmail);
            return "error_invalid_register.html";
        } else {
            User user = userMapper.map(userRestaurantDTO).withRole(2);
            Restaurant restaurant = restaurantMapper.mapFromDTO(userRestaurantDTO);
            userService.saveUser(user, restaurant);
            return "redirect:/";
        }
    }
}
