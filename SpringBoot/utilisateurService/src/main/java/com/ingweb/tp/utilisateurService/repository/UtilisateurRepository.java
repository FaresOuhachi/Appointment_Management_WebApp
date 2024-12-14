package com.ingweb.tp.utilisateurService.repository;

import com.ingweb.tp.utilisateurService.model.Role;
import com.ingweb.tp.utilisateurService.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByNom(String nom);
    List<Utilisateur> findByRole(Role roleName);
}