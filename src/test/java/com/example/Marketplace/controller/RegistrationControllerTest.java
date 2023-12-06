package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    private User registerUser;

    @BeforeEach
    public void setUp(){
        registerUser = new User();
        registerUser.setUsername("newUser");
        registerUser.setPw("password");
    }

    @AfterEach
    public void tearDown() {
        registerUser = null;
    }

    @Test
    public void testRegistrationPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_form"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegistrationSuccess() throws Exception {
        String username = "newUser";
        String password = "password";

        // mock the repository to return false when checkUserExists so the user can be registered
        when(userRepository.checkUserExists(username)).thenReturn(false);
        // mock user injection
        mockMvc.perform(post("/register")
                    .param("username", username)
                    .param("pw", password))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_success"));
    }

    @Test
    public void testRegistrationFailure() throws Exception {
        String username = "newUser";
        String password = "password";

        // mock the repository to return true to simulate user already existing
        when(userRepository.checkUserExists(username)).thenReturn(true);
        // mock user injection
        mockMvc.perform(post("/register")
                    .param("username", username)
                    .param("pw", password))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_form"))
                .andExpect(model().attributeExists("registrationError"));
    }

}
