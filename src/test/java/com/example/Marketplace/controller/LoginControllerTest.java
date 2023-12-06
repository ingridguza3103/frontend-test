package com.example.Marketplace.controller;


import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setUsername("mockUser");
        mockUser.setPw(passwordEncoder.encode("mockPassword"));
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        String username = "mockUser";
        String correctPw = "mockPassword";

        when(userRepository.checkUserExists(username)).thenReturn(true);


        when(userRepository.findByUserName(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(correctPw, mockUser.getPw())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("username", username)
                        .param("pw", correctPw))
                .andExpect(status().isOk())
                .andExpect(view().name("login_success"));
    }

    @Test
    public void testIncorrectPassword() throws Exception {
        String username = "mockUser";
        String wrongPw = "notMockPassword";
        when(userRepository.checkUserExists(username)).thenReturn(true);


        when(userRepository.findByUserName(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(wrongPw, mockUser.getPw())).thenReturn(false);

        mockMvc.perform(post("/login")
                        .param("username", username)
                        .param("pw", wrongPw))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("loginError"));
    }

    @Test
    public void userNotExistingTest() throws Exception {
        String username = "mockUser";
        String password = "password";

        when(userRepository.checkUserExists(username)).thenReturn(false);
        //when(userRepository.findByUserName(username)).thenReturn(null);

        mockMvc.perform(post("/login")
                .param("username", username)
                .param("pw", password))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(model().attributeExists("loginError"));
    }


}