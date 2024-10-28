package tpweb.disponibiliteService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpweb.disponibiliteService.model.Disponibilite;
import tpweb.disponibiliteService.repository.DisponibiliteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibiliteService {
    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteRepository.findAll();
    }

    public Optional<Disponibilite> getDisponibiliteById(Long id) {
        return disponibiliteRepository.findById(id);
    }

    public Disponibilite createDisponibilite(Disponibilite disponibilite) {
        return disponibiliteRepository.save(disponibilite);
    }

    public Disponibilite updateDisponibilite(Long id, Disponibilite disponibilite) {
        disponibilite.setId(id);
        return disponibiliteRepository.save(disponibilite);
    }

    public void deleteDisponibilite(Long id) {
        disponibiliteRepository.deleteById(id);
    }
}
