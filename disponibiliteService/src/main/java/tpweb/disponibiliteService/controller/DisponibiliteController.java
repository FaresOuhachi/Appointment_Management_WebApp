package tpweb.disponibiliteService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpweb.disponibiliteService.model.Disponibilite;
import tpweb.disponibiliteService.service.DisponibiliteService;

import java.util.List;

@RestController
@RequestMapping("/disponibilites")
public class DisponibiliteController {
    @Autowired
    private DisponibiliteService disponibiliteService;

    @GetMapping
    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilite> getDisponibiliteById(@PathVariable Long id) {
        Disponibilite disponibilite = disponibiliteService.findById(id);
        return disponibilite != null ? ResponseEntity.ok(disponibilite) : ResponseEntity.notFound().build();
    }

    @GetMapping("/professional/{professionalId}")
    public List<Disponibilite> getDisponibiliteByProfessionalId(@PathVariable Long professionalId) {
        return disponibiliteService.findByProfessionalId(professionalId);
    }

    @PostMapping
    public Disponibilite createDisponibilite(@RequestBody Disponibilite disponibilite) {
        return disponibiliteService.save(disponibilite);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Disponibilite> updateDisponibilite(@PathVariable Long id, @RequestBody Disponibilite disponibiliteDetails) {
        Disponibilite disponibilite = disponibiliteService.findById(id);
        if (disponibilite == null) {
            return ResponseEntity.notFound().build();
        }
        disponibilite.setDateDebut(disponibiliteDetails.getDateDebut());
        disponibilite.setDateFin(disponibiliteDetails.getDateFin());
        disponibilite.setProfessionalId(disponibiliteDetails.getProfessionalId());
        return ResponseEntity.ok(disponibiliteService.save(disponibilite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilite(@PathVariable Long id) {
        disponibiliteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}