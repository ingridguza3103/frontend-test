package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This class handles all REST methods regarding the user registration
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@GetMapping("/register")
    public String viewHomePage() {
        return "index";
    }*/

    /**
     * This method handles the GET request for the registration
     * @param model the modle
     * @return registration_form.html
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "index";
    }

    /**
     * This method handles all POST requests, so basically the user registration and validation
     * if the username already exists or not
     * @param user the User object created from the user provided input
     * @param model the model
     * @return registration_success.html if registration successful, registration_form.html
     * otherwise
     */
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // check if user already exists and only save if not
        if (!userRepository.checkUserExists(user.getUsername())) {
            String encodedPassword = passwordEncoder.encode(user.getPw());
            System.out.println("Insert user " + user.getUsername() + " with pw " + user.getPw());
            user.setPw(encodedPassword);
            userRepository.saveAndFlush(user);
            return "registration_success";
        } else {
            // TODO: Print username already exists to the model
            model.addAttribute("registrationError", "Username already exists");
            return "index"; // Return to registration form with error message
        }


    }
}