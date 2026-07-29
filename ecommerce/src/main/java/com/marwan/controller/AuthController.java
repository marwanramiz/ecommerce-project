package com.marwan.controller;

import com.marwan.model.User;
import com.marwan.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        return userService.findByEmail(loginRequest.getEmail())
                .filter(userEntity -> userEntity.getPassword().equals(loginRequest.getPassword()))
                .map(userEntity -> ResponseEntity.ok(Map.of(
                        "message", "login successfull",
                        "id", userEntity.getId(),
                        "email", userEntity.getEmail(),
                        "role", userEntity.getRole().name()
                )))
                .orElse(ResponseEntity.status(401).body(Map.of(
                        "error", "Invalid credentials"
                )));
    }
}