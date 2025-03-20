package com.example.clientmanagement.controller;

import com.example.clientmanagement.dto.ProductDTO;
import com.example.clientmanagement.mapper.ProductMapper;
import com.example.clientmanagement.model.Product;
import com.example.clientmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur responsable de la gestion des produits.
 * Fournit les endpoints REST pour les opérations CRUD sur les produits.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    /** Service pour la gestion des produits */
    @Autowired
    private ProductService productService;

    /** Mapper pour la conversion entre entités et DTOs */
    @Autowired
    private ProductMapper productMapper;

    /**
     * Récupère la liste de tous les produits.
     * @return Liste contenant tous les produits enregistrés dans le système
     */
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return (List<ProductDTO>) productMapper.asProductDTOs(productService.getAllProducts());
    }

    /**
     * Récupère un produit spécifique par son ID.
     * @param id L'identifiant unique du produit
     * @return Le produit correspondant à l'ID fourni
     */
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productMapper.asProductDTO(productService.getProduct(id));
    }

    /**
     * Crée un nouveau produit dans le système.
     * @param productDTO Les informations du produit à créer
     * @return Le produit créé avec son ID généré
     */
    @PostMapping
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productMapper.asProductDTO(productService.createProduct(productMapper.asProduct(productDTO)));
    }

    /**
     * Met à jour les informations d'un produit existant.
     * @param id L'identifiant du produit à modifier
     * @param productDTO Les nouvelles informations du produit
     * @return Le produit mis à jour
     */
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Product product = productMapper.asProduct(productDTO);
        product.setId(id);
        return productMapper.asProductDTO(productService.updateProduct(product));
    }

    /**
     * Supprime un produit du système.
     * @param id L'identifiant du produit à supprimer
     * @return Réponse vide avec le statut OK si la suppression est réussie
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}