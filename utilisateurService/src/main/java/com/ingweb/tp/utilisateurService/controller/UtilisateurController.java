package com.ingweb.tp.utilisateurService.controller;

import com.ingweb.tp.utilisateurService.model.Utilisateur;
import com.ingweb.tp.utilisateurService.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        return utilisateur != null ? ResponseEntity.ok(utilisateur) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.save(utilisateur);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setRole(utilisateurDetails.getRole());
        return ResponseEntity.ok(utilisateurService.save(utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}