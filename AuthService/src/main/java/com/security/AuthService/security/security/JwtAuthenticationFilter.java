package com.security.AuthService.security.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String token = extractTokenFromRequest(request);
        DecodedJWT decodedJWT = jwtTokenProvider.decodeJwt(token);

        String role = decodedJWT.getClaim("role").asString();  // Correct claim extraction

        // Perform authentication logic (this example just creates an Authentication object)
        return super.attemptAuthentication(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);  // Remove the "Bearer " prefix
        }
        return null;
    }
}
