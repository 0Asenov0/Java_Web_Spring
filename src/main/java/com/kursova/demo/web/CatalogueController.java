package com.kursova.demo.web;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {

      private final CarService carService;


    public CatalogueController(CarService carService1) {
        this.carService = carService1;

    }

    @GetMapping("")
    public String getCatalogue(Model model){
        List<CarDto> listOfCars = carService.getAllCars();
        model.addAttribute("listOfCars",listOfCars);
        return "catalogue";
    }

    @GetMapping("/{id}")
    public String carInfo(@PathVariable("id")Long id, Model model){
        CarDto carDto = carService.getCarById(id);
        model.addAttribute("carDto",carDto);
        return "car-info";
    }

    @GetMapping("/add/auto")
    public String addListing(){
        return "add-auto";
    }
    @PostMapping("/add/auto")
       public String addCar(@ModelAttribute CarDto carDto){
       boolean isAdded = carService.addCar(carDto);
        if(isAdded){
            return "catalogue";
        }
        return "add-auto";
    }
}
