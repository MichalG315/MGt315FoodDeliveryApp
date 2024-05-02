package pl.zajavka.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class OrderController {

    static final String ORDER = "/order";

    @GetMapping(value = ORDER)
    public String getOrder(){



        return "order_page";
    }

}
