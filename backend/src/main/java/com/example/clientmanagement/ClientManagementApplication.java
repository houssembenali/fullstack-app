package com.example.clientmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Point d'entrée principal de l'application de gestion des clients.
 * Cette classe configure l'application Spring Boot et les paramètres CORS.
 */
@SpringBootApplication
public class ClientManagementApplication {

    /**
     * Méthode principale qui démarre l'application Spring Boot.
     * @param args Arguments de ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientManagementApplication.class, args);
    }

    /**
     * Configure les paramètres CORS pour permettre les requêtes cross-origin.
     * Autorise les requêtes depuis le frontend Angular (localhost:4200)
     * pour toutes les routes API.
     * 
     * @return Configuration CORS personnalisée
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}