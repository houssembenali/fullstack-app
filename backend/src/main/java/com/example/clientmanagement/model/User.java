package com.example.clientmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entité représentant un utilisateur du système.
 * Cette classe gère les informations d'authentification et les rôles des utilisateurs.
 */
@Entity
@Table(name = "users")
@Data
public class User {
    /** Identifiant unique de l'utilisateur, généré automatiquement */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nom d'utilisateur unique pour l'authentification */
    @Column(unique = true, nullable = false)
    private String username;

    /** Mot de passe crypté de l'utilisateur */
    @Column(nullable = false)
    private String password;

    /** Rôle de l'utilisateur dans le système */
    private String role = "ROLE_USER";

    @CreationTimestamp
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;
    /**
     * Version timestamp for optimistic locking and tracking modifications.
     * Automatically updated when the entity is modified.
     */
    @Version
    @UpdateTimestamp
    private LocalDateTime version;
}