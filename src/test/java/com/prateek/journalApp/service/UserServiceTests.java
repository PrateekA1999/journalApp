package com.prateek.journalApp.service;


import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Should return user when username exists")
    public void testFindByUserName() {
        User expectedUser = User.builder()
                .userName("Ram")
                .password("Ram@123")
                .roles(List.of("USER"))
                .build();
        when(userRepository.findByUserName("Ram")).thenReturn(expectedUser);

        User user = userService.findByUserName("Ram");

        assertNotNull(user);
        assertEquals(expectedUser.getUserName(), user.getUserName());
        assertEquals(expectedUser.getPassword(), user.getPassword());
        assertTrue(user.getRoles().contains("USER"));

        verify(userRepository).findByUserName("Ram");
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    @DisplayName("Should return true when user is created")
    public void testCreateEntry(User user) {
        assertTrue(userService.createEntry(user));
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
