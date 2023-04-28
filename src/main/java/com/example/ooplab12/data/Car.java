package com.example.ooplab12.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String model;
    private int releaseYear;
    private int price;
    private String registrationNumber;
}
