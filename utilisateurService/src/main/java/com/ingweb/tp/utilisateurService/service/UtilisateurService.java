package com.ingweb.tp.utilisateurService.service;

import com.ingweb.tp.utilisateurService.model.Utilisateur;
import com.ingweb.tp.utilisateurService.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur inscrire(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur authentifier(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
            return utilisateur;
        }
        return null;
    }

    public Optional<Utilisateur> modifierInformation(Long id, Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setNom(utilisateurDetails.getNom());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
            return utilisateurRepository.save(utilisateur);
        });
    }
}