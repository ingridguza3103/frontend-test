package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user_entity", new User());

        return "registration_form";
    }

    @PostMapping("/register_user")
    public String registerUser(User user_entity) {
        String encodedPassword = passwordEncoder.encode(user_entity.getPw());
        System.out.println("Insert user " + user_entity.getUsername() + " with pw " + user_entity.getPw());
        user_entity.setPw(encodedPassword);
        userRepository.saveAndFlush(user_entity);

        return "register_success";
    }
}