package com.prateek.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    @Disabled("Tested")
    void testSendEmail() {
        assertTrue(emailService.sendEmail("prateekkumar.kumar44@gmail.com", "Test", "This is a test email"));
    }
}
