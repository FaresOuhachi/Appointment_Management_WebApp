package tpweb.rendezVousService.service;

import org.springframework.stereotype.Service;
import tpweb.rendezVousService.model.RendezVous;
import tpweb.rendezVousService.repository.RendezVousRepository;

import java.util.List;

@Service
public class RendezVousService {
    private final RendezVousRepository rendezVousRepository;

    public RendezVousService(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }

    public RendezVous findById(Long id) {
        return rendezVousRepository.findById(id).orElse(null);
    }

    public RendezVous save(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    public void deleteById(Long id) {
        rendezVousRepository.deleteById(id);
    }

    public List<RendezVous> findByClientId(Long clientId) {
        return rendezVousRepository.findByClientId(clientId);
    }

    public List<RendezVous> findByProfessionalId(Long professionalId) {
        return rendezVousRepository.findByProfessionalId(professionalId);
    }
}