package com.ingweb.tp.utilisateurService.service;

import com.ingweb.tp.utilisateurService.model.Client;
import com.ingweb.tp.utilisateurService.model.Professionnel;
import com.ingweb.tp.utilisateurService.repository.ClientRepository;
import com.ingweb.tp.utilisateurService.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final ClientRepository clientRepository;
    private final ProfessionnelRepository professionnelRepository;

    @Autowired
    public AdminService(ClientRepository clientRepository, ProfessionnelRepository professionnelRepository) {
        this.clientRepository = clientRepository;
        this.professionnelRepository = professionnelRepository;
    }

    // CRUD for Client
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setNom(clientDetails.getNom());
        client.setEmail(clientDetails.getEmail());
        client.setMotDePasse(clientDetails.getMotDePasse());
        client.setNumDeTelephone(clientDetails.getNumDeTelephone());
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // CRUD for Professionnel
    public Professionnel addProfessionnel(Professionnel professionnel) {
        return professionnelRepository.save(professionnel);
    }

    public List<Professionnel> getAllProfessionnels() {
        return professionnelRepository.findAll();
    }

    public Professionnel updateProfessionnel(Long id, Professionnel professionnelDetails) {
        Professionnel professionnel = professionnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found"));
        professionnel.setNom(professionnelDetails.getNom());
        professionnel.setEmail(professionnelDetails.getEmail());
        professionnel.setSpecialite(professionnelDetails.getSpecialite());
        return professionnelRepository.save(professionnel);
    }

    public void deleteProfessionnel(Long id) {
        professionnelRepository.deleteById(id);
    }
}
