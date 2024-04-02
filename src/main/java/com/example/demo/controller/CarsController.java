package com.example.demo.controller;

import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "sortBy", required = false) String sortBy,
                           @RequestParam(value = "count", required = false) Optional<Long> count, Model model) {
        model.addAttribute("cars", carService.getCars(sortBy, count));
        return "cars";
    }
}
