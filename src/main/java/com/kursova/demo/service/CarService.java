package com.kursova.demo.service;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.dto.CarSummaryDto;
import com.kursova.demo.models.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CarService {

    CarDto getCarById(Long id);

    List<CarDto> getAllCars();

    Page<CarDto> getAllMostPreferredCars(Pageable pageable);

    boolean addCar(CarDto carDto);
}
