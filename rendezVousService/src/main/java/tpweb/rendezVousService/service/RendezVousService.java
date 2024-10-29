package tpweb.rendezVousService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpweb.rendezVousService.model.RendezVous;
import tpweb.rendezVousService.repository.RendezVousRepository;

import java.util.List;

@Service
public class RendezVousService {
    @Autowired
    private RendezVousRepository rendezVousRepository;

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

    public List<RendezVous> findByProfessionnelId(Long professionnelId) {
        return rendezVousRepository.findByProfessionnelId(professionnelId);
    }
}