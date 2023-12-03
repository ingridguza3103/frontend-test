package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import com.example.Marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("")
    public String viewHomepage(Model model){
        model.addAttribute("user", new User());
        return "index";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute  User user) {
        // Simple placeholder for now
        // Implement authentication logic here
        // TODO: check if user exists

        // TODO: authenticate user

        // TODO: print username or password incorrect if incorrect input

        // TODO: if input correct route to homepage
        // save user to the database
        System.out.println(user.toString());

        User insertedUser = userRepo.save(user);
        // Return a JSON response indicating successful login
        return ResponseEntity.ok().body("{ " + insertedUser.toString() + " \"login\": true}");
    }
}