package com.kursova.demo.repository;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.models.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {

    CarEntity findByCarModel(String model);
}
