package com.example.clientmanagement.repository;

import com.example.clientmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository pour la gestion des utilisateurs dans la base de données.
 * Fournit les opérations CRUD standard via l'héritage de JpaRepository
 * ainsi que des méthodes de recherche personnalisées.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * @param username Le nom d'utilisateur à rechercher
     * @return Un Optional contenant l'utilisateur s'il existe
     */
    Optional<User> findByUsername(String username);
}