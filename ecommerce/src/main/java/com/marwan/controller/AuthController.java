package com.marwan.controller;

import com.marwan.model.User;
import com.marwan.service.UserService;
import com.marwan.security.JwtUtil;   // <-- import your JwtUtil
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        return userService.findByEmail(loginRequest.getEmail())
                .filter(userEntity -> userEntity.getPassword().equals(loginRequest.getPassword()))
                .map(userEntity -> {
                    String token = jwtUtil.generateToken(userEntity.getEmail()); // generate JWT
                    return ResponseEntity.ok(Map.of(
                            "message", "login successfull",
                            "id", userEntity.getId(),
                            "email", userEntity.getEmail(),
                            "role", userEntity.getRole().name(),
                            "token", token   // <-- include token in response
                    ));
                })
                .orElse(ResponseEntity.status(401).body(Map.of(
                        "error", "Invalid credentials"
                )));
    }
}
