package com.kursova.demo.service.impl;

import com.kursova.demo.models.UserActivationCodeEntity;
import com.kursova.demo.models.UserEntity;
import com.kursova.demo.models.events.UserRegisterEvent;
import com.kursova.demo.models.events.UserWelcomeEvent;
import com.kursova.demo.repository.UserActivationCodeRepository;
import com.kursova.demo.repository.UserRepository;
import com.kursova.demo.service.EmailService;
import com.kursova.demo.service.UserActivationService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {

   private static final String ACTIVATION_CODE_SYMBOLS = "ASDFGHJKLQWERTYUIOPZXCVBNMqwertyuiopasdfghjklzxcvbnm123456789";
   private static final  int ACTIVATION_CODE_LENGTH = 20;
   private final EmailService emailService;
   private final UserRepository userRepository;

   private final ApplicationEventPublisher applicationEventPublisher;
   private final UserActivationCodeRepository userActivationCodeRepository;

    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher, UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @Override
    @EventListener(UserRegisterEvent.class)
    public void userRegister(UserRegisterEvent event) {
        String activationCode =createActivationCode(event.getUserEmail());

        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserName(), activationCode);
        System.out.println("User with mail-> "+event.getUserEmail());
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {

        //TODO DELETE

    }

    @Override
    public boolean setActivationStatus(String activationCode) {
       UserActivationCodeEntity userActivationCodeEntity = userActivationCodeRepository.findByActivationCode(activationCode);
        if(!userActivationCodeEntity.equals(null)){
            UserEntity user = userRepository.findById(userActivationCodeEntity.getUser().getId()).get();
            user.setActive(true);
          //  userRepository.save(user);
            emailService.sendActivatedAccount(user.getEmail(), user.getFamilyName() +" "+user.getFirstName());
            return true;
        }
        return false;

    }

    @Override
    public String createActivationCode(String userEmail) {

        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException(UserEntity.class,"User not found")));

        userActivationCodeRepository.save(userActivationCodeEntity);

        return activationCode;
    }
    public static String generateActivationCode(){
        StringBuilder activationCode = new StringBuilder();
        Random random  = new SecureRandom();
        for(int i = 0; i < ACTIVATION_CODE_LENGTH; i++){
            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }
        return activationCode.toString();
     }
}
