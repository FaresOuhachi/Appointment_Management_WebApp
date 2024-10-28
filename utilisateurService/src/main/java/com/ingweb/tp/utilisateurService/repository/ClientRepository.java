package com.ingweb.tp.utilisateurService.repository;

import com.ingweb.tp.utilisateurService.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
}
