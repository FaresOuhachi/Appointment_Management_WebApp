package tpweb.rendezVousService.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

public class Utilisateur {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nom")
    private String nom;

    @JsonIgnore
    private String motDePasse;

    @JsonProperty("email")
    private String email;

    @JsonProperty("role")
    @Enumerated(EnumType.STRING)
    private Role role;

}
