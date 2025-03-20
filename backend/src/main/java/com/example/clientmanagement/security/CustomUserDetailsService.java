package com.example.clientmanagement.security;

import com.example.clientmanagement.model.User;
import com.example.clientmanagement.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service personnalisé pour charger les détails des utilisateurs.
 * Implémente l'interface UserDetailsService de Spring Security pour gérer l'authentification des utilisateurs.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repository pour accéder aux données des utilisateurs stockées en base de données.
     */
    private final UserRepository userRepository;

    /**
     * Constructeur qui initialise le service avec le repository des utilisateurs.
     * 
     * @param userRepository Le repository pour accéder aux données des utilisateurs
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Charge les détails d'un utilisateur à partir de son nom d'utilisateur.
     * Cette méthode est appelée par Spring Security lors du processus d'authentification.
     *
     * @param username Le nom d'utilisateur à rechercher
     * @return Les détails de l'utilisateur conformes à l'interface UserDetails de Spring Security
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé dans la base de données
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())
        ));
    }
}