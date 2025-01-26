package com.kursova.demo.service.impl;

import com.kursova.demo.dto.RentDto;
import com.kursova.demo.models.RentEntity;
import com.kursova.demo.models.UserEntity;
import com.kursova.demo.repository.CarRepository;
import com.kursova.demo.repository.RentRepository;
import com.kursova.demo.repository.UserRepository;
import com.kursova.demo.service.DetailsUserServ;
import com.kursova.demo.service.RentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kursova.demo.mapper.Mapping.mapRentDtoToRent;

@Service
public class RentServiceImpl implements RentService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    private final RentRepository rentRepository;

    public RentServiceImpl(CarRepository carRepository, UserRepository userRepository, RentRepository rentRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }

    @Override
    public boolean addRentRequest(RentDto rentDto,Long id) {

                    List<Long> users = rentRepository.searchRented(rentDto.getStartDate(), rentDto.getEndDate());
                    if (users.isEmpty()) {
                        UserEntity user = userRepository.findByEmail(rentDto.getUsername()).get();
                        RentEntity rentEntity = mapRentDtoToRent(rentDto);
                        rentEntity.setContact(rentDto.getContact());
                        rentEntity.setCarId(id);
                        rentEntity.setUserId(user.getId());
                        rentRepository.save(rentEntity);
                        return true;
                    }
            return false;
    }

}
