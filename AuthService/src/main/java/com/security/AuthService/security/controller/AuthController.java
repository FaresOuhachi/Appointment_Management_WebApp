package com.security.AuthService.security.controller;


import com.security.AuthService.security.model.Utilisateur;
import com.security.AuthService.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody Utilisateur utilisateur) {
        return authService.register(utilisateur);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Utilisateur utilisateur) {
        return authService.login(utilisateur);
    }
}
