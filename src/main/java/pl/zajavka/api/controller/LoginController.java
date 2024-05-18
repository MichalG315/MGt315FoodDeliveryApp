package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    public static final String LOGIN = "/login.html";

    @GetMapping(value = LOGIN)
    public String getLogin() {
        return "login.html";
    }
}
