package com.kursova.demo.service;

import com.kursova.demo.dto.UserProfileInfoDTO;
import com.kursova.demo.dto.UserRegisterDto;


public interface UserService {

    boolean registerUser(UserRegisterDto userRegisterDto);
    UserProfileInfoDTO getUser(String name);
    boolean passwordMatch(UserRegisterDto userRegisterDto);

    void updateUser(Long id ,UserProfileInfoDTO userProfileInfoDTO);


    UserProfileInfoDTO findById(Long id);
}
