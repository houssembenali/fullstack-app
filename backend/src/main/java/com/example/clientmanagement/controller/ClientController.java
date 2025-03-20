package com.example.clientmanagement.controller;

import com.example.clientmanagement.dto.ClientDTO;
import com.example.clientmanagement.mapper.ClientMapper;
import com.example.clientmanagement.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur responsable de la gestion des clients.
 * Fournit les endpoints REST pour les opérations CRUD sur les clients.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    /** Service pour la gestion des clients */
    @Autowired
    private ClientService clientService;

    /** Mapper pour la conversion entre entités et DTOs */
    @Autowired
    private ClientMapper clientMapper;

    /**
     * Récupère la liste de tous les clients.
     * @return Liste contenant tous les clients enregistrés dans le système
     */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok((List<ClientDTO>) clientMapper.asClientDTOs(clientService.getAllClients()));
    }

    /**
     * Crée un nouveau client dans le système.
     * @param clientDTO Les informations du client à créer, fournies dans le corps de la requête
     * @return Le client créé avec son ID généré
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientMapper.asClientDTO(clientService.createClient(clientMapper.asClient(clientDTO))));
    }
}