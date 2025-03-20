package com.example.clientmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entité représentant un client dans le système.
 */
@Entity
@Data
public class Client {
    /** Identifiant unique du client, généré automatiquement */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Prénom du client */
    private String firstName;

    /** Nom de famille du client */
    private String lastName;

    /** Adresse email du client */
    private String email;

    /** Numéro de téléphone du client */
    private String phone;

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