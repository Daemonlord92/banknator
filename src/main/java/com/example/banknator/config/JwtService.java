package com.example.banknator.config;

import com.example.banknator.entity.UserCredential;
import com.example.banknator.users.UserService;
import com.example.banknator.users.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    private JwtConfigProperty jwtConfigProperty;

    private final UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(String email) {
        Optional<UserCredential> user = userService.getUserCredentialByEmail(email);
        if(user.isEmpty()) {
            throw new EntityNotFoundException("User Not Found");
        }
        User user1 = userService.getUserById(user.get().getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.get().getRole());
        claims.put("firstName", user1.getFirstName());
        return generateToken(claims, user.get());
    }

    public String generateToken(UserDetails userDetails) {
        Optional<UserCredential> user = userService.getUserCredentialByEmail(userDetails.getUsername());
        if(user.isEmpty()) {
            throw new EntityNotFoundException("User Not Found");
        }
        User user1 = userService.getUserById(user.get().getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", user1.getFirstName());
        claims.put("role", user.get().getRole());
        return generateToken(claims, userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperty.getExpiration()))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperty.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

