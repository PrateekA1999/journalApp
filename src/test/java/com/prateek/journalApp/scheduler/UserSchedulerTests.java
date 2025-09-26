package com.prateek.journalApp.scheduler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserSchedulerTests {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    @Disabled("Tested")
    void testFetchUsersAndSendSAEmail() {
        userScheduler.fetchUsersAndSendSAEmail();
        assertTrue(true);
    }
}
