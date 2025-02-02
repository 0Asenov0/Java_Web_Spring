package com.kursova.demo.mapper;

import com.kursova.demo.dto.CarDto;
import com.kursova.demo.dto.RentDto;
import com.kursova.demo.dto.UserProfileInfoDTO;
import com.kursova.demo.dto.UserRegisterDto;
import com.kursova.demo.models.CarEntity;
import com.kursova.demo.models.RentEntity;
import com.kursova.demo.models.UserEntity;
import com.kursova.demo.models.UserRoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Calendar;

public class Mapping {

    public static UserRoleEntity mapToUserRoleEntity(UserProfileInfoDTO userProfileInfoDTO){

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        if(userProfileInfoDTO.getRole().name().equals("ADMIN")) {
            userRoleEntity.setId(1L);
        }
        else{
            userRoleEntity.setId(2L);
        }
        userRoleEntity.setRole(userProfileInfoDTO.getRole());
        return userRoleEntity;
    }
    public static  UserProfileInfoDTO mapToUserProfileDto(UserEntity userEntity){
        UserProfileInfoDTO userProfileInfoDTO =new UserProfileInfoDTO();
        userProfileInfoDTO.setId(userEntity.getId());
        userProfileInfoDTO.setEmail(userEntity.getEmail());
        userProfileInfoDTO.setFirstName(userEntity.getFirstName());
        userProfileInfoDTO.setLastName(userEntity.getFamilyName());
        return userProfileInfoDTO;
    }
    public static  RentEntity mapRentDtoToRent(RentDto rentDto){
        RentEntity rentEntity = new RentEntity();
        rentEntity.setContact(rentDto.getContact());
        rentEntity.setStartDate(rentDto.getStartDate());
        rentEntity.setEndDate(rentDto.getEndDate());
        return rentEntity;
    }
    public static  CarDto mapCarToDto(CarEntity carEntity){
        CarDto carDto = new CarDto();
        carDto.setId(carEntity.getId());
        carDto.setCarMaker(carEntity.getCarMaker());
        carDto.setCarModel(carEntity.getCarModel());
        carDto.setHp(carEntity.getHp());
        carDto.setDailyRentPrice(carEntity.getDailyRentPrice());
        carDto.setAcceleration(carEntity.getAcceleration());
        carDto.setDescription(carEntity.getDescription());
        carDto.setMainPhotoUrl(carEntity.getMainPhotoUrl());
        carDto.setSecondary_url1(carDto.getSecondary_url1());
        carDto.setSecondaryUrl2(carDto.getSecondaryUrl2());
        carDto.setSecondaryUrl3(carDto.getSecondaryUrl3());
        carDto.setSecondaryUrl4(carEntity.getSecondaryUrl4());
        carDto.setCatchPhrase(carEntity.getCatchPhrase());
        carDto.setIntroduction(carEntity.getIntroduction());

        return carDto;
    }

    public static CarEntity mapDtoToCar(CarDto carDto){
        CarEntity car = new CarEntity();
        car.setCarMaker(carDto.getCarMaker());
        car.setCarModel(carDto.getCarModel());
        car.setHp(carDto.getHp());
        car.setDailyRentPrice(carDto.getDailyRentPrice());
        car.setAcceleration(carDto.getAcceleration());
        car.setCatchPhrase(carDto.getCatchPhrase());
        car.setIntroduction(carDto.getIntroduction());
        car.setDescription(carDto.getDescription());
        car.setMainPhotoUrl(carDto.getMainPhotoUrl());
        car.setSecondary_url1(carDto.getSecondary_url1());
        car.setSecondaryUrl2(carDto.getSecondaryUrl2());
        car.setSecondaryUrl3(carDto.getSecondaryUrl3());
        car.setSecondaryUrl4(carDto.getSecondaryUrl4());
        return car;
    }



}
