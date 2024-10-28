package com.ingweb.tp.utilisateurService.controller;

import com.ingweb.tp.utilisateurService.model.Utilisateur;
import com.ingweb.tp.utilisateurService.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/inscrire")
    public Utilisateur inscrire(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.inscrire(utilisateur);
    }

    @PostMapping("/authentifier")
    public ResponseEntity<Utilisateur> authentifier(@RequestParam String email, @RequestParam String motDePasse) {
        Utilisateur utilisateur = utilisateurService.authentifier(email, motDePasse);
        return utilisateur != null ? ResponseEntity.ok(utilisateur) : ResponseEntity.status(401).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierInformation(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> updatedUtilisateur = utilisateurService.modifierInformation(id, utilisateurDetails);
        return updatedUtilisateur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}