package com.kursova.demo.service;

public interface EmailService {
    void sendRegistrationEmail(String userEmail,
                               String userName,
                               String activationCode);

    void sendActivatedAccount(String userEmail,String username);
}
