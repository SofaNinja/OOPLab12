package com.example.ooplab12.controller;

import com.example.ooplab12.data.Car;
import com.example.ooplab12.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @GetMapping("/cars")
    public String printCars(Model model) {
        model.addAttribute("cars", carService.getCars());
        return "cars";
    }

    @PostMapping("/add_car")
    public String addCar(
            @RequestParam("car_id") int id,
            @RequestParam("car_model") String model,
            @RequestParam("car_releaseYear") int releaseYear,
            @RequestParam("car_price") int price,
            @RequestParam("car_registrationNumber") String registrationNumber
    ) {
        Car car = new Car(id, model, releaseYear, price, registrationNumber);
        carService.add(car);
        return "redirect:/cars";
    }

    @GetMapping("/delete_car")
    public String deleteCar(@RequestParam int id) {
        carService.deleteById(id);
        return "redirect:/cars";
    }

    @GetMapping("/save_cars")
    public String saveCars() {
        carService.save();
        return "redirect:/cars";
    }
    @GetMapping("/read_cars")
    public String readCars() {
        carService.read();
        return "redirect:/cars";
    }

    @PostMapping("/sort_by_model")
    public String sortByModel(@RequestParam String model) {
        carService.sortByModel(model);
        return "redirect:/cars";
    }

    @PostMapping("/sort_by_model_and_year")
    public String sortByModelAndYear(@RequestParam String model, @RequestParam int releaseYear) {
        carService.sortByModelAndYears(model, releaseYear);
        return "redirect:/cars";
    }

    @PostMapping("/sort_by_price")
    public String sortByYearAndPrice(@RequestParam int releaseYear, @RequestParam int price) {
        carService.sortByYearsAndPrice(releaseYear, price);
        return "redirect:/cars";
    }
}