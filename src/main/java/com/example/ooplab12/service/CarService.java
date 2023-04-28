package com.example.ooplab12.service;

import com.example.ooplab12.data.Car;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Getter
    public List<Car> cars;

    @PostConstruct
    public void init() {
        read();
    }

    public void add(Car car) {
        cars.add(car);
    }

    public void delete(Car car) {
        cars.remove(car);
    }

    public void deleteById(int id) {
        cars.removeIf(c -> c.getId() == id);
    }

    public void save() {
        try (PrintWriter out = new PrintWriter("cars.txt")) {
            for (Car car : cars) {
                out.println(car.getId() + "," + car.getModel() + "," + car.getReleaseYear() + ","
                        + car.getPrice() + "," + car.getRegistrationNumber());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void read() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("cars.txt"))) {
            cars = new ArrayList<>(reader.lines().map(line -> {
                String[] s = line.split(",");
                int id = Integer.parseInt(s[0]);
                String model = s[1];
                int releaseYear = Integer.parseInt(s[2]);
                int price = Integer.parseInt(s[3]);
                String registrationNumber = s[4];
                return new Car(id, model, releaseYear, price, registrationNumber);
            }).toList());
        } catch (IOException e) {
            cars = new ArrayList<>();
        }
    }

    public void sortByModel(String model) {
        List<Car> tempCars =new ArrayList<>();
        for(Car car: cars) if(car.getModel().equals(model)) tempCars.add(car);
        cars = tempCars;
    }

    public void sortByModelAndYears(String model, int releaseYear) {
        sortByModel(model);
        List<Car> tempCars =new ArrayList<>();
        for (Car car: cars) if(Year.now().getValue() - car.getReleaseYear() > releaseYear) tempCars.add(car);
        cars = tempCars;
    }

    public void sortByYearsAndPrice(int releaseYear, int price) {
        List<Car> tempCars = new ArrayList<>();
        for (Car car: cars) if(car.getReleaseYear() == releaseYear && car.getPrice() > price) tempCars.add(car);
        cars = tempCars;
    }
}