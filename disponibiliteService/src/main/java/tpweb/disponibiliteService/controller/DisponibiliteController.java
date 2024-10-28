package tpweb.disponibiliteService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpweb.disponibiliteService.model.Disponibilite;
import tpweb.disponibiliteService.service.DisponibiliteService;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilites")
public class DisponibiliteController {
    @Autowired
    private DisponibiliteService disponibiliteService;

    @GetMapping
    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteService.getAllDisponibilites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilite> getDisponibiliteById(@PathVariable Long id) {
        return disponibiliteService.getDisponibiliteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Disponibilite createDisponibilite(@RequestBody Disponibilite disponibilite) {
        return disponibiliteService.createDisponibilite(disponibilite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilite> updateDisponibilite(@PathVariable Long id, @RequestBody Disponibilite disponibilite) {
        return ResponseEntity.ok(disponibiliteService.updateDisponibilite(id, disponibilite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilite(@PathVariable Long id) {
        disponibiliteService.deleteDisponibilite(id);
        return ResponseEntity.noContent().build();
    }
}