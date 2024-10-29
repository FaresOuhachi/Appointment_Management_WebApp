package tpweb.rendezVousService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpweb.rendezVousService.model.RendezVous;

import java.util.List;

@Repository

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByClientId(Long clientId);
    List<RendezVous> findByProfessionnelId(Long professionnelId);
}