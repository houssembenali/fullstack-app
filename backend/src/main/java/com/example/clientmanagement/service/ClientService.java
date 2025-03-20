package com.example.clientmanagement.service;

import com.example.clientmanagement.model.Client;
import com.example.clientmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service responsable de la gestion des clients.
 * Fournit les méthodes pour créer, lire, mettre à jour et supprimer des clients.
 */
@Service
public class ClientService {

    /** Repository pour accéder et manipuler les données des clients */
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Récupère la liste de tous les clients.
     * @return Liste contenant tous les clients enregistrés
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Récupère un client par son identifiant.
     * @param id L'identifiant du client recherché
     * @return Un Optional contenant le client s'il existe, vide sinon
     */
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    /**
     * Crée un nouveau client dans la base de données.
     * @param client Les informations du client à créer
     * @return Le client créé avec son ID généré
     */
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Met à jour les informations d'un client existant.
     * @param id L'identifiant du client à mettre à jour
     * @param updatedClient Les nouvelles informations du client
     * @return Un Optional contenant le client mis à jour s'il existe, vide sinon
     */
    public Optional<Client> updateClient(Client updatedClient) {
        return clientRepository.findById(updatedClient.getId())
                .map(existingClient -> {
                    existingClient.setFirstName(updatedClient.getFirstName());
                    existingClient.setLastName(updatedClient.getLastName());
                    existingClient.setEmail(updatedClient.getEmail());
                    existingClient.setPhone(updatedClient.getPhone());
                    return clientRepository.save(existingClient);
                });
    }

    /**
     * Supprime un client par son identifiant.
     * @param id L'identifiant du client à supprimer
     * @return true si le client a été supprimé, false s'il n'existait pas
     */
    public boolean deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}