package pl.zajavka.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zajavka.api.dto.UserCustomerDTO;
import pl.zajavka.api.dto.mapper.CustomerMapper;
import pl.zajavka.api.dto.mapper.RestaurantMapper;
import pl.zajavka.api.dto.mapper.UserMapper;
import pl.zajavka.business.UserService;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.User;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(RegisterRestController.API_REGISTER_PAGE)
public class RegisterRestController {

    public static final String API_REGISTER_PAGE = "/api/register";
    public static final String CUSTOMER_DONE = "/customer/save";
    public static final String RESTAURANT_DONE = "/restaurant/save";

    private final UserMapper userMapper;
    private final UserService userService;

    private final CustomerMapper customerMapper;
    private final RestaurantMapper restaurantMapper;

    @PostMapping(API_REGISTER_PAGE + CUSTOMER_DONE)
    public ResponseEntity<?> successfulCustomerRegistration(
            @Valid @RequestBody UserCustomerDTO userCustomerDTO
    ) {
        String newUserName = userService.checkIfUserNameExists(userCustomerDTO.getUserName());
        String newEmail = userService.checkIfEmailExists(userCustomerDTO.getEmail());
        if (!Objects.isNull(newUserName)) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body("The username you entered already exists. Please choose another one");
        } else if (!Objects.isNull(newEmail)) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body("The email you entered already exists. Please choose another one");
        } else {
            User user = userMapper.map(userCustomerDTO).withRole(1);
            Customer customer = customerMapper.map(userCustomerDTO);
            userService.saveUser(user, customer);
            return ResponseEntity.ok().build();
        }
    }
}

