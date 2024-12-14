package com.security.AuthService.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

public class JwtTokenProvider {

    private JwtEncoder jwtEncoder;

    public JwtTokenProvider(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String createJwt(Authentication authentication) {
        // Get the user principal and roles
        User userPrincipal = (User) authentication.getPrincipal();

        // Use the JwtEncoder to create a JWT (signed with private key)
        JwtEncoderParameters parameters = JwtEncoderParameters.fromClaims(claims -> {
            claims.put("sub", userPrincipal.getUsername());
            claims.put("role", userPrincipal.getAuthorities());
        });

        return jwtEncoder.encode(parameters).getTokenValue();
    }

    public DecodedJWT decodeJwt(String token) {
        // Decodes the JWT using the NimbusJwtDecoder
        return JWT.decode(token);
    }
}
