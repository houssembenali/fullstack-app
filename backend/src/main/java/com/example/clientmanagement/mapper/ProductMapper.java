package com.example.clientmanagement.mapper;

import com.example.clientmanagement.dto.ProductDTO;
import com.example.clientmanagement.model.Product;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import org.springframework.stereotype.Component;
import fr.xebia.extras.selma.IoC;
/**
 * Interface de mapping entre l'entité Product et son DTO.
 */


@Component
@Mapper(withIgnoreMissing = fr.xebia.extras.selma.IgnoreMissing.ALL, withIoC = IoC.SPRING)
public interface ProductMapper {
    
    @Maps
    ProductDTO asProductDTO(Product product);

    @Maps
    Product asProduct(ProductDTO productDTO);

    default Iterable<ProductDTO> asProductDTOs(Iterable<Product> products) {
        if (products == null) return null;
        java.util.List<ProductDTO> dtos = new java.util.ArrayList<>();
        for (Product product : products) {
            dtos.add(asProductDTO(product));
        }
        return dtos;
    }
}