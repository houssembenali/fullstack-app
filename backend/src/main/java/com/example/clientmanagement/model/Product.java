package com.example.clientmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "products")
@Data
public class Product {
    /**
     * L'identifiant unique du produit.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Le nom du produit.
     * Ce champ est obligatoire et ne peut pas être vide.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * La description détaillée du produit.
     * Ce champ est optionnel et peut contenir des informations supplémentaires sur le produit.
     */
    private String description;

    /**
     * Le prix du produit.
     * Doit être une valeur positive et ne peut pas être nul.
     */
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    /**
     * La quantité en stock du produit.
     * Doit être une valeur positive et ne peut pas être nul.
     */
    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be positive")
    private Integer stock;

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