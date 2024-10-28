package com.ingweb.tp.utilisateurService.controller;

import com.ingweb.tp.utilisateurService.model.Client;
import com.ingweb.tp.utilisateurService.model.Professionnel;
import com.ingweb.tp.utilisateurService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // CRUD operations for Client
    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return new ResponseEntity<>(adminService.addClient(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(adminService.getAllClients(), HttpStatus.OK);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return new ResponseEntity<>(adminService.updateClient(id, clientDetails), HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        adminService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // CRUD operations for Professionnel
    @PostMapping("/professionnels")
    public ResponseEntity<Professionnel> addProfessionnel(@RequestBody Professionnel professionnel) {
        return new ResponseEntity<>(adminService.addProfessionnel(professionnel), HttpStatus.CREATED);
    }

    @GetMapping("/professionnels")
    public ResponseEntity<List<Professionnel>> getAllProfessionnels() {
        return new ResponseEntity<>(adminService.getAllProfessionnels(), HttpStatus.OK);
    }

    @PutMapping("/professionnels/{id}")
    public ResponseEntity<Professionnel> updateProfessionnel(@PathVariable Long id, @RequestBody Professionnel professionnelDetails) {
        return new ResponseEntity<>(adminService.updateProfessionnel(id, professionnelDetails), HttpStatus.OK);
    }

    @DeleteMapping("/professionnels/{id}")
    public ResponseEntity<Void> deleteProfessionnel(@PathVariable Long id) {
        adminService.deleteProfessionnel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}