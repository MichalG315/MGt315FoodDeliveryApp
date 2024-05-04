package pl.zajavka.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.dto.UserDTO;
import pl.zajavka.api.dto.mapper.UserMapper;
import pl.zajavka.business.UserService;
import pl.zajavka.domain.User;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CustomerRegisterController {
    static final String REGISTER = "/register/customer";
    static final String REGISTER_DONE = "/register/customer/save";

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping(value = REGISTER)
    public ModelAndView registerPage() {

        Map<String, ?> model = Map.of("userDTO", UserDTO.buildDefault());

        return new ModelAndView("register_customer_page", model);
    }

    @PostMapping(REGISTER_DONE)
    public String successfulRegistration(
            @Valid @ModelAttribute("userDTO") UserDTO userDTO
    ) {
        User user = userMapper.map(userDTO).withRole(1);
        userService.saveUser(user);
        return "redirect:/";
    }
}
