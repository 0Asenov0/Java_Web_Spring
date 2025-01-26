package com.kursova.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {


    @GetMapping("/shop")
    public String shopView(){

        return "shop";
    }
}
