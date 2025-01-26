package com.kursova.demo.web;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
  private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public String homePage(Model model, @PageableDefault(
            size = 3,
            sort = "id"
    )Pageable pageable){
        Page<CarDto> mostPreferredCars =  carService.getAllMostPreferredCars(pageable);
        model.addAttribute("preferredCars",mostPreferredCars);
        return "index";
    }
}
