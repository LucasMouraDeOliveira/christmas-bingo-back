package com.lordkadoc.bingo_back.user;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET = "a-random-secret-key-which-is-long-enough-to-get-no-error";

    public String generateToken(UserDetails user) {
        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
            .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    public boolean isValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        SecretKey key = getSecretKey();
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }
    
}
