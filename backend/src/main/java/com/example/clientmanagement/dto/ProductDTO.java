package com.example.clientmanagement.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * DTO pour le transfert des données des produits.
 */
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private LocalDateTime dateCreation;
    private LocalDateTime version;
}