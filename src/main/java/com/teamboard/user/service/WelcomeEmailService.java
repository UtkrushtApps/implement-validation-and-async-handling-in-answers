package com.teamboard.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WelcomeEmailService {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeEmailService.class);

    @Async
    public void sendWelcomeEmail(String email, String fullName) {
        logger.info("Simulating sending welcome email to {} <{}>", fullName, email);
        try {
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException ignored) {}
        logger.info("Welcome email sent to {}!", email);
    }
}
