package com.prateek.journalApp.service;


import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindByUserName() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("Ram@123").roles(List.of("USER")).build());
        User user = userRepository.findByUserName("Ram");
        assertEquals("Ram",user.getUserName());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 2, 4",
            "3, 3, 6"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
