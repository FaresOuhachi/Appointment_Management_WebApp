package tpweb.disponibiliteService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpweb.disponibiliteService.model.Disponibilite;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
}
