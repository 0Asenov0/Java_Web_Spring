package com.kursova.demo.web;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.dto.RentDto;
import com.kursova.demo.service.CarService;
import com.kursova.demo.service.RentService;
import com.kursova.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RentController {
     private final CarService carService;
     private final RentService rentService;
     private final UserService userService;

    public RentController(CarService carService, RentService rentService, UserService userService) {
        this.carService = carService;
        this.rentService = rentService;
        this.userService = userService;
    }

    @GetMapping("/cars/rent/{id}")
    public String getRentForm(@PathVariable("id")Long id, Model model){
        CarDto carDto = carService.getCarById(id);
        model.addAttribute("car",carDto);
        return "rent-form";
    }
    @PostMapping("/cars/rent/{id}")
    public String saveListing(@ModelAttribute RentDto rentDto,@PathVariable("id")Long id,Model model) {
        boolean success = rentService.addRentRequest(rentDto,id);
        if(success) return "redirect:/";
        else {
            CarDto carDto = carService.getCarById(id);
            model.addAttribute("unsuccessful_rent", "true");
            model.addAttribute("car",carDto);
            return "rent-form";
        }
    }
}
