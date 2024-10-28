package com.ingweb.tp.utilisateurService.model;

import com.ingweb.tp.utilisateurService.model.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Professionnel extends Utilisateur {

    private String specialite;
}
