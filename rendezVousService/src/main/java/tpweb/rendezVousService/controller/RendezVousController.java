package tpweb.rendezVousService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpweb.rendezVousService.model.RendezVous;
import tpweb.rendezVousService.service.RendezVousService;

import java.util.List;

@RestController
@RequestMapping("/rendezvous")
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping
    public List<RendezVous> getAllRendezVous() {
        return rendezVousService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.findById(id);
        return rendezVous != null ? ResponseEntity.ok(rendezVous) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public RendezVous createRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousService.save(rendezVous);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVousDetails) {
        RendezVous rendezVous = rendezVousService.findById(id);
        if (rendezVous == null) {
            return ResponseEntity.notFound().build();
        }
        rendezVous.setDateHeure(rendezVousDetails.getDateHeure());
        rendezVous.setStatut(rendezVousDetails.getStatut());
        rendezVous.setProfessionnelId(rendezVousDetails.getProfessionnelId());
        rendezVous.setClientId(rendezVousDetails.getClientId());
        return ResponseEntity.ok(rendezVousService.save(rendezVous));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{clientId}")
    public List<RendezVous> getRendezVousByClientId(@PathVariable Long clientId) {
        return rendezVousService.findByClientId(clientId);
    }

    @GetMapping("/professionnel/{professionnelId}")
    public List<RendezVous> getRendezVousByProfessionnelId(@PathVariable Long professionnelId) {
        return rendezVousService.findByProfessionnelId(professionnelId);
    }
}