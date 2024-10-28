package tpweb.rendezVousService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpweb.rendezVousService.model.RendezVous;

@Repository

public interface RendezVousRepository extends JpaRepository<RendezVous, Long>{
}
