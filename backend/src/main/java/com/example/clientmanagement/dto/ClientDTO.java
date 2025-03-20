package com.example.clientmanagement.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * DTO pour le transfert des données des clients.
 */
@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime dateCreation;
    private LocalDateTime version;
}