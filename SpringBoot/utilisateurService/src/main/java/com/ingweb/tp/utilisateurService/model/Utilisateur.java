package com.ingweb.tp.utilisateurService.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

