package com.kursova.demo.service;

import com.kursova.demo.enums.UserRoleEnum;
import com.kursova.demo.models.UserEntity;
import com.kursova.demo.models.UserRoleEntity;
import com.kursova.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

public class DetailsUserServ implements UserDetailsService {
    private final UserRepository userRepository;

    public DetailsUserServ(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return  userRepository
                .findByEmail(email)
                .map (DetailsUserServ::mapToUserDetails)
                .orElseThrow(() ->  new UsernameNotFoundException("User "+ email +" not found!"));

    }
    private static UserDetails mapToUserDetails(UserEntity userEntity){
        return  User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities((userEntity.getRoles().stream().map(DetailsUserServ::map).toList()))
                .build();
    }

    private static GrantedAuthority map(UserRoleEntity userRoleEntity){
        return new SimpleGrantedAuthority(
                "ROLE_" + userRoleEntity.getRole().name()
        );
    }
}
