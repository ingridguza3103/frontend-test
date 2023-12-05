package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@WebMvcTest(LoginController.class)
@ComponentScan(basePackages = "com.example.Marketplace")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Test
    void testLoginSuccess() throws Exception {
        // Create a mock user
        User mockUser = new User();
        mockUser.setUsername("johndoe");
        mockUser.setPw("$2a$10$6.t9l1FqCedXVh6pOh3HauyjLe44Z9ZxZcIjeT6vlyhzJjS7NE2ZC"); // Encoded password for "password"

        // Stub the UserRepository to return the mock user when queried by username
        when(userRepository.findByUserName("johndoe")).thenReturn(mockUser);

        // Perform a login request with the correct credentials
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "johndoe")
                        .param("pw", "password"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login_success"));
    }

}
