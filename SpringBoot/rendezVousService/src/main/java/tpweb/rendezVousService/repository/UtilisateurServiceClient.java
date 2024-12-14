package tpweb.rendezVousService.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import tpweb.rendezVousService.model.Utilisateur;

@FeignClient(name = "utilisateur-service",url = "localhost:8081/utilisateurs", fallback = UtilisateurServiceFallback.class)
public interface UtilisateurServiceClient {
    @GetMapping("/{id}")
    Utilisateur getUtilisateurById(@PathVariable("id") Long id);
}

