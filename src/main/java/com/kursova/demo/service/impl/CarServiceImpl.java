package com.kursova.demo.service.impl;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.models.CarEntity;
import com.kursova.demo.repository.CarRepository;
import com.kursova.demo.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kursova.demo.mapper.Mapping.mapCarToDto;
import static com.kursova.demo.mapper.Mapping.mapDtoToCar;

@Service
public class CarServiceImpl implements CarService {
     private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto getCarById(Long id) {
        CarEntity car = carRepository.findById(id).get();
        CarDto carDto = mapCarToDto(car);
        return carDto;

    }

    @Override
    public List<CarDto> getAllCars() {
         return carRepository.findAll().stream().map(e -> mapCarToDto(e)).collect(Collectors.toList());
    }

    @Override
    public Page<CarDto> getAllMostPreferredCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(e->mapCarToDto(e));
    }

    @Override
    public boolean addCar(CarDto carDto) {
        if(carRepository.findByCarModel(carDto.getCarModel()) == null ) {
            CarEntity carEntity = mapDtoToCar(carDto);

            carRepository.save(carEntity);
            return true;
        }
        return false;
    }

}
