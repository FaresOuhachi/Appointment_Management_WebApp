package com.ingweb.tp.utilisateurService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingweb.tp.utilisateurService.model.Utilisateur;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Utilisateur findByEmail(String email);
}
