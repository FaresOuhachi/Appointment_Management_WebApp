package tpweb.rendezVousService.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpweb.rendezVousService.model.*;
import tpweb.rendezVousService.repository.DisponibiliteServiceClient;
import tpweb.rendezVousService.repository.UtilisateurServiceClient;
import tpweb.rendezVousService.service.RendezVousService;

import java.util.List;

@RestController
@RequestMapping("/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @Autowired
    private DisponibiliteServiceClient disponibiliteServiceClient;

    @Autowired
    private UtilisateurServiceClient utilisateurServiceClient;

    @GetMapping
    public List<RendezVous> getAllRendezVous() {
        return rendezVousService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.findById(id);
        return rendezVous != null ? ResponseEntity.ok(rendezVous) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<RendezVousDetails> getRendezVousDetailsById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.findById(id);
        if (rendezVous == null) {
            return ResponseEntity.notFound().build();
        }

        // Retrieve data from other services
        Utilisateur client = utilisateurServiceClient.getUtilisateurById(rendezVous.getClientId());
        Utilisateur professional = utilisateurServiceClient.getUtilisateurById(rendezVous.getProfessionalId());
        Disponibilite disponibilite = disponibiliteServiceClient.getDisponibiliteById(rendezVous.getDisponibiliteId());

        // Set the details
        RendezVousDetails details = new RendezVousDetails();
        details.setId(rendezVous.getId());
        details.setStatut(rendezVous.getStatut());
        details.setClient(client);
        details.setProfessional(professional);
        details.setDisponibilite(disponibilite);

        return ResponseEntity.ok(details);
    }

    @GetMapping("/client/{clientId}")
    public List<RendezVous> getRendezVousByClientId(@PathVariable Long clientId) {
        return rendezVousService.findByClientId(clientId);
    }

    @GetMapping("/professional/{professionalId}")
    public List<RendezVous> getRendezVousByProfessionalId(@PathVariable Long professionalId) {
        return rendezVousService.findByProfessionalId(professionalId);
    }
    
    @PostMapping
    public ResponseEntity<?> createRendezVous(@RequestBody RendezVous rendezVous) {
        // Validate if the client exists
        Utilisateur client = utilisateurServiceClient.getUtilisateurById(rendezVous.getClientId());
        if (client == null) {
            return ResponseEntity.badRequest().body("Client with ID " + rendezVous.getClientId() + " does not exist.");
        }

        // Validate if the professional exists
        Utilisateur professional = utilisateurServiceClient.getUtilisateurById(rendezVous.getProfessionalId());
        if (professional == null) {
            return ResponseEntity.badRequest().body("Professional with ID " + rendezVous.getProfessionalId() + " does not exist.");
        }

        // Validate if the Disponibilite exists
        Disponibilite disponibilite = disponibiliteServiceClient.getDisponibiliteById(rendezVous.getDisponibiliteId());
        if (disponibilite == null) {
            return ResponseEntity.badRequest().body("Disponibilite with ID " + rendezVous.getDisponibiliteId() + " does not exist.");
        }

        // Additional validation: Check if the time fits the Disponibilite range
        if (disponibilite.getDateDebut().isBefore(disponibilite.getDateDebut()) ||
                disponibilite.getDateFin().isAfter(disponibilite.getDateFin())) {
            return ResponseEntity.badRequest().body("RendezVous time does not fit within the Disponibilite's range.");
        }

        // Save the valid RendezVous
        RendezVous savedRendezVous = rendezVousService.save(rendezVous);
        return ResponseEntity.ok(savedRendezVous);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVousDetails) {
        RendezVous rendezVous = rendezVousService.findById(id);
        if (rendezVous == null) {
            return ResponseEntity.notFound().build();
        }
        rendezVous.setStatut(rendezVousDetails.getStatut());
        rendezVous.setProfessionalId(rendezVousDetails.getProfessionalId());
        rendezVous.setClientId(rendezVousDetails.getClientId());
        return ResponseEntity.ok(rendezVousService.save(rendezVous));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}