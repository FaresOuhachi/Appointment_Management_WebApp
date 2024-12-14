package com.security.AuthService.security.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.jose.jwk.*;


@Configuration
public class AuthorizationServerConfig {

    @Bean
    public JwtEncoder jwtEncoder() throws Exception {
        // Generate RSA Key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // Create RSA JWK for signing
        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();

        // Create JWK Set and return NimbusJwtEncoder
        JWKSet jwkSet = new JWKSet(jwk);
        return new NimbusJwtEncoder(jwkSet);
    }

    @Bean
    public NimbusJwtDecoder jwtDecoder(RSAPublicKey publicKey) {
        // Use publicKey for decoding the JWT
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
}
