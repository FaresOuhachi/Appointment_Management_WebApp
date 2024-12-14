package com.security.AuthService.security.service;

import com.security.AuthService.security.model.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtEncoder jwtEncoder;

    public AuthService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateJwtToken(Authentication authentication) {
        return jwtEncoder.encode(authentication).getTokenValue();
    }

    public String register(Utilisateur utilisateur) {
        return "User registered successfully";
    }

    public String login(Utilisateur utilisateur) {
        return "User logged in successfully";
    }

    public String logout() {
        return "User logged out successfully";
    }

    public String deleteAccount() {
        return "User account deleted successfully";
    }

    public String updateAccount() {
        return "User account updated successfully";
    }
}