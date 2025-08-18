package com.prateek.journalApp.service;

import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return user when username exists")
    void loadUserByUsernameTest() {
        when(userRepository.findByUserName("Ram@123")).thenReturn(User.builder().userName("Ram@123").password("Ram@123").roles(List.of("USER")).build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram@123");
        Assertions.assertNotNull(user);
    }
}
