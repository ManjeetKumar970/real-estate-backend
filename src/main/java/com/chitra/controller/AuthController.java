package com.chitra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chitra.entity.Auth;
import com.chitra.service.AuthService;
import com.chitra.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Auth auth) {
        String password = auth.getPassword();
        password = authService.hashPassword(password);
        auth.setPassword(password);
        String email = auth.getEmail();
        if (!authService.emailValidation(email)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email already exists");
            return response;
        }
        authService.register(auth);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User saved successfully");

        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Auth auth) {

        String email = auth.getEmail();
        String password = auth.getPassword();

        Auth existingAuth = authService.chheckEmail(email);

        Map<String, String> response = new HashMap<>();

        if (existingAuth == null
                || !authService.passwordMatches(password, existingAuth.getPassword())) {

            response.put("message", "Invalid email or password");
            return response;
        }

        String token = jwtUtil.generateToken(existingAuth);

        response.put("message", "Login successful");
        response.put("token", token);

        return response;
    }

}
