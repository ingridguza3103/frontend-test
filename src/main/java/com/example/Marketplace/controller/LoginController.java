package com.example.Marketplace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // Simple placeholder for now
        // Implement authentication logic here

        // Return a JSON response indicating successful login
        return ResponseEntity.ok().body("{\"login\": true}");
    }
}