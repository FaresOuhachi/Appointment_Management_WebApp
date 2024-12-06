package tpweb.rendezVousService.repository;

import org.springframework.stereotype.Component;
import tpweb.rendezVousService.model.Utilisateur;

@Component
public class UtilisateurServiceFallback implements UtilisateurServiceClient {
    @Override
    public Utilisateur getUtilisateurById(Long id) {
        throw new RuntimeException("Utilisateur Service not available");
    }
}
