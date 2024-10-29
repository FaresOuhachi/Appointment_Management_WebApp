package tpweb.disponibiliteService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpweb.disponibiliteService.model.Disponibilite;

import java.util.List;

@Repository
public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
    List<Disponibilite> findByProfessionnelId(Long professionnelId);
}