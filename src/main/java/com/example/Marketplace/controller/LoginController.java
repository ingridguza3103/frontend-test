package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "index";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute  User user) {
        // Simple placeholder for now
        // Implement authentication logic here
        // TODO: check if user exists
        if (userRepo.checkUserExists(user.getUsername())) {

            System.out.println("USER EXISTS");
            // retrieve user from userRepo
            User loginUser = userRepo.findByUserName(user.getUsername());

            // authenticate user
            if (passwordEncoder.matches(user.getPw(), loginUser.getPw())) { // pw correct
                System.out.println("PW Correct");
                // TODO: if input correct route to homepage
                return ResponseEntity.ok().body("{ " + user.toString() + " \"login\": true}");
            } else { // pw incorrect
                System.out.println("PW Incorrect");
                // TODO: print username or password incorrect if incorrect input
                return ResponseEntity.ok().body("{ " + user.toString() + " \"login\": false}");
            }



        } else {
            // TODO: PRINT user does not exist
            System.out.println("USER NOT EXISTING");
        }


        // Return a JSON response indicating successful login
        return ResponseEntity.ok().body("{ " + user.toString() + " \"login\": false}");
    }


}