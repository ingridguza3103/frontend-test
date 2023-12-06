package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This class handles all REST calls regarding the login
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * GET request handler for login loads the login form
     * @param model the model
     * @return index.html
     */
    @GetMapping("")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    /**
     * This method handles all POST requests and performs user authentication
     * @param user the User object created from username and password from the login form
     * @param model the model
     * @return index.html if login failed, login_success.html if login succeeded
     */
    @PostMapping("/login")
    public String login(@ModelAttribute  User user, Model model) {
        // Simple placeholder for now
        // Implement authentication logic here
        // TODO: check if user exists
        if (userRepository.checkUserExists(user.getUsername())) {

            System.out.println("USER EXISTS");
            // retrieve user from userRepo
            User loginUser = userRepository.findByUserName(user.getUsername());

            // authenticate user
            if (passwordEncoder.matches(user.getPw(), loginUser.getPw())) { // pw correct
                System.out.println("PW Correct");
                // TODO: if input correct route to homepage
                return "login_success";
            } else { // pw incorrect
                System.out.println("PW Incorrect");
                // TODO: print username or password incorrect if incorrect input
                model.addAttribute("loginError", "Username or password incorrect!");
                return "index";
            }



        } else {
            // TODO: PRINT user does not exist
            System.out.println("USER NOT EXISTING");
            model.addAttribute("loginError", "User does not exist");
            return "index";

        }



    }


}