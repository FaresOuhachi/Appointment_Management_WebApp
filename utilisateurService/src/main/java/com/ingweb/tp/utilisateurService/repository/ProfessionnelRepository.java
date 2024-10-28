package com.ingweb.tp.utilisateurService.repository;

import com.ingweb.tp.utilisateurService.model.Professionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionnelRepository extends JpaRepository<Professionnel, Long>{

}
