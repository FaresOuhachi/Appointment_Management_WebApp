package tpweb.disponibiliteService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpweb.disponibiliteService.model.Disponibilite;
import tpweb.disponibiliteService.service.DisponibiliteService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

//    @GetMapping("/{id}")
//    public Integer getDisponibilitePrixById(@PathVariable Long id) {
//        Disponibilite disponibilite = disponibiliteService.findById(id);
//        return disponibilite.getPrix();
//    }

    @GetMapping("/professionnel/{professionnelId}")
    public List<Disponibilite> getDisponibiliteByProfessionnelId(@PathVariable Long professionnelId) {
        return disponibiliteService.findByProfessionnelId(professionnelId);
    }

//    @GetMapping("/searchByPrix")
//    public List<Disponibilite> getAllDisponibilitesWithPriceX(@RequestParam("prix") String prix){
//        Integer price = Integer.parseInt(prix);
//        return disponibiliteService.findByPrix(price);
//    }

//    @GetMapping("/professionnel/{professionnelId}")
//    public AtomicReference<Integer> getSumOfDisponibiliteById(@PathVariable Long professionnelId){
//        List<Disponibilite> disponibilites = disponibiliteService.findByProfessionnelId(professionnelId);
//        AtomicReference<Integer> SumOfDisponibilitePrice = new AtomicReference<>(0);
//        disponibilites.forEach( disponibilite -> SumOfDisponibilitePrice.updateAndGet(v -> v + disponibilite.getPrix()));
//
//        return SumOfDisponibilitePrice;
//        }



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
        disponibilite.setDateHeure(disponibiliteDetails.getDateHeure());
        disponibilite.setDuree(disponibiliteDetails.getDuree());
        disponibilite.setProfessionnelId(disponibiliteDetails.getProfessionnelId());
//        disponibilite.setPrix(disponibiliteDetails.getPrix());
        return ResponseEntity.ok(disponibiliteService.save(disponibilite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilite(@PathVariable Long id) {
        disponibiliteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}