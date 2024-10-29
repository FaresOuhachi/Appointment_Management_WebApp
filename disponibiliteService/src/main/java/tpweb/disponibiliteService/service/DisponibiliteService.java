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

    public List<Disponibilite> findAll() {
        return disponibiliteRepository.findAll();
    }

    public Disponibilite findById(Long id) {
        return disponibiliteRepository.findById(id).orElse(null);
    }

    public Disponibilite save(Disponibilite disponibilite) {
        return disponibiliteRepository.save(disponibilite);
    }

    public void deleteById(Long id) {
        disponibiliteRepository.deleteById(id);
    }

    public List<Disponibilite> findByProfessionnelId(Long professionnelId) {
        return disponibiliteRepository.findByProfessionnelId(professionnelId);
    }
}