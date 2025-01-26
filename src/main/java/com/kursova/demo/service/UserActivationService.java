package com.kursova.demo.service;

import com.kursova.demo.models.events.UserRegisterEvent;

public interface UserActivationService {
    void userRegister(UserRegisterEvent event);
    void cleanUpObsoleteActivationLinks();
    boolean setActivationStatus(String activationCode);

    String createActivationCode(String userEmail);
}
