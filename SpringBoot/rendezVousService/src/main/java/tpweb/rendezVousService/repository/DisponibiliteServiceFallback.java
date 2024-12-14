package tpweb.rendezVousService.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import tpweb.rendezVousService.model.Disponibilite;

@Component
public class DisponibiliteServiceFallback implements DisponibiliteServiceClient {
    @Override
    public Disponibilite getDisponibiliteById(@PathVariable("id") Long id) {
        throw new RuntimeException("Disponibilite Service not available");
    }
}
