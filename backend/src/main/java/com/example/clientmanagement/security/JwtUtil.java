package com.example.clientmanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utilitaire pour la gestion des tokens JWT (JSON Web Token).
 * Cette classe fournit des méthodes pour générer, valider et extraire des informations des tokens JWT.
 */
@Service
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtUtil(
            @Value("${jwt.expiration}") Long expiration) {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expiration = expiration;
    }

    /**
     * Extrait le nom d'utilisateur du token JWT.
     * 
     * @param token Le token JWT à analyser
     * @return Le nom d'utilisateur extrait du token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait la date d'expiration du token JWT.
     * 
     * @param token Le token JWT à analyser
     * @return La date d'expiration du token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait une revendication spécifique du token JWT en utilisant une fonction de résolution.
     * 
     * @param token Le token JWT à analyser
     * @param claimsResolver La fonction pour extraire la revendication souhaitée
     * @return La revendication extraite du token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait toutes les revendications du token JWT.
     * 
     * @param token Le token JWT à analyser
     * @return Toutes les revendications contenues dans le token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Vérifie si le token JWT est expiré.
     * 
     * @param token Le token JWT à vérifier
     * @return true si le token est expiré, false sinon
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Génère un nouveau token JWT pour un utilisateur.
     * 
     * @param userDetails Les détails de l'utilisateur pour lequel générer le token
     * @return Le token JWT généré
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crée un token JWT avec les revendications spécifiées.
     * 
     * @param claims Les revendications à inclure dans le token
     * @param subject Le sujet du token (généralement le nom d'utilisateur)
     * @return Le token JWT créé
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valide un token JWT pour un utilisateur donné.
     * Vérifie que le token correspond à l'utilisateur et n'est pas expiré.
     * 
     * @param token Le token JWT à valider
     * @param userDetails Les détails de l'utilisateur pour la validation
     * @return true si le token est valide pour l'utilisateur, false sinon
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}