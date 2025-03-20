package com.example.clientmanagement.controller;

import com.example.clientmanagement.model.User;
import com.example.clientmanagement.repository.UserRepository;
import com.example.clientmanagement.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur responsable de la gestion de l'authentification des utilisateurs.
 * Fournit les endpoints pour la connexion et l'inscription des utilisateurs.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /** Gestionnaire d'authentification pour valider les identifiants utilisateur */
    private final AuthenticationManager authenticationManager;
    
    /** Repository pour accéder et manipuler les données des utilisateurs */
    private final UserRepository userRepository;
    
    /** Utilitaire pour générer et gérer les tokens JWT */
    private final JwtUtil jwtUtil;
    
    /** Encodeur pour sécuriser les mots de passe des utilisateurs */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructeur pour injecter les dépendances nécessaires.
     * @param authenticationManager Le gestionnaire d'authentification
     * @param userRepository Le repository des utilisateurs
     * @param jwtUtil L'utilitaire JWT
     * @param passwordEncoder L'encodeur de mot de passe
     */
    public AuthController(AuthenticationManager authenticationManager,
                         UserRepository userRepository,
                         JwtUtil jwtUtil,
                         PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Endpoint pour authentifier un utilisateur et générer un token JWT.
     * @param loginRequest Map contenant le nom d'utilisateur et le mot de passe
     * @return ResponseEntity contenant le token JWT en cas de succès
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.get("username"),
                        loginRequest.get("password")
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint pour inscrire un nouvel utilisateur.
     * @param registerRequest Map contenant le nom d'utilisateur et le mot de passe du nouvel utilisateur
     * @return ResponseEntity avec un message de succès ou d'erreur
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerRequest) {
        if (userRepository.findByUsername(registerRequest.get("username")).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.get("username"));
        user.setPassword(passwordEncoder.encode(registerRequest.get("password")));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}