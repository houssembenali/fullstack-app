package com.example.clientmanagement.repository;

import com.example.clientmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour la gestion des clients dans la base de données.
 * Fournit les opérations CRUD standard via l'héritage de JpaRepository.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}