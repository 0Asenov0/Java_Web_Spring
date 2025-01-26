package com.kursova.demo.service.impl;

import com.kursova.demo.dto.RentSummaryDTO;
import com.kursova.demo.dto.UserProfileInfoDTO;
import com.kursova.demo.dto.UserRegisterDto;
import com.kursova.demo.models.CarEntity;
import com.kursova.demo.models.RentEntity;
import com.kursova.demo.models.UserEntity;
import com.kursova.demo.models.UserRoleEntity;
import com.kursova.demo.models.events.UserRegisterEvent;
import com.kursova.demo.repository.CarRepository;
import com.kursova.demo.repository.RentRepository;
import com.kursova.demo.repository.UserRepository;
import com.kursova.demo.repository.UserRoleRepository;
import com.kursova.demo.service.CurrentUser;
import com.kursova.demo.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.kursova.demo.mapper.Mapping.mapToUserProfileDto;
import static com.kursova.demo.mapper.Mapping.mapToUserRoleEntity;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserRoleRepository userRoleRepository;
    private final CarRepository carRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RentRepository rentRepository, ApplicationEventPublisher applicationEventPublisher, UserRoleRepository userRoleRepository, CarRepository carRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rentRepository = rentRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userRoleRepository = userRoleRepository;
        this.carRepository = carRepository;
        this.currentUser = currentUser;
    }
    @Override
    public boolean passwordMatch(UserRegisterDto userRegisterDto) {
        return userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword());
    }

    @Override
    public void updateUser(Long id , UserProfileInfoDTO userProfileInfoDTO) {

        UserEntity user = userRepository.findById(id).get();
        user.setFirstName(userProfileInfoDTO.getFirstName());
        user.setFamilyName(userProfileInfoDTO.getLastName());
        user.setEmail(userProfileInfoDTO.getEmail());
       List<UserRoleEntity>  listOfUser = user.getRoles();
       listOfUser.add(mapToUserRoleEntity(userProfileInfoDTO));
       user.setRoles(listOfUser);
        userRepository.save(user);
    }

    @Override
    public UserProfileInfoDTO findById(Long id) {
         return mapToUserProfileDto(userRepository.findById(id).get());
    }


    @Override
    public boolean registerUser(UserRegisterDto userRegisterDto) {
        String email = userRegisterDto.getEmail();
           if(userRepository.findByEmail(email).isEmpty()){
               UserEntity user = new UserEntity();
               userRepository.save(mapToUser(userRegisterDto));
               applicationEventPublisher.publishEvent(new UserRegisterEvent("UserService",
                       userRegisterDto.getEmail(),
                       userRegisterDto.getFirstName()+" " + userRegisterDto.getLastName()));
               return true;
           }
       return false;
    }


    public UserProfileInfoDTO getUser(String email){
        UserProfileInfoDTO userInfo = null;
        UserEntity user = userRepository.findByEmail(email).get();

        if(!user.getOrders().isEmpty()){
         userInfo = mapToUserProfileDto(user);

       List<RentEntity> rentEntity =  rentRepository.findByUserId(user.getId());
            List<RentSummaryDTO> rentSummaryDTO = new ArrayList<>();
            RentSummaryDTO temp = new RentSummaryDTO();
            CarEntity car = new CarEntity();

        for(RentEntity rent : rentEntity){

            car = carRepository.findById(rent.getCarId()).get();
            temp.setCarModel(car.getCarModel());
            temp.setCarMaker(car.getCarMaker());
            temp.setStartDate(rent.getStartDate());
            temp.setEndDate(rent.getEndDate());
            rentSummaryDTO.add(temp);
        }
          userInfo.setSummaryCarRent(rentSummaryDTO);
          userInfo.setId(user.getId());

        return userInfo;
        }

        return mapToUserProfileDto(user);

    }
    public   UserEntity mapToUser(UserRegisterDto userDto){
        UserEntity user = new UserEntity();
        user.setActive(false);
        user.setFirstName(userDto.getFirstName());
        user.setFamilyName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setDayCreated(Calendar.getInstance().getTime());
        user.setOrders(new ArrayList<>());
        return user;

    }

}
