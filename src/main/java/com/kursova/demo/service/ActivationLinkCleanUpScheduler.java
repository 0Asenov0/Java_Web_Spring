package com.kursova.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationLinkCleanUpScheduler {
    private final UserActivationService userActivationService;

    public ActivationLinkCleanUpScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "5 * * * *")
    public void cleanUp(){
        System.out.println("Trigger clean up activation Links");
        userActivationService.cleanUpObsoleteActivationLinks();
    }
}
