package com.security.AuthService.security.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String motDePasse;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
