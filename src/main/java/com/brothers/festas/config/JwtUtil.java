package com.brothers.festas.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.SignatureException;


import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final String base64Secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        if (secret == null || secret.isEmpty()) {
            this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            this.base64Secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } else {
            byte[] decodedSecret = Base64.getDecoder().decode(secret);
            this.secretKey = Keys.hmacShaKeyFor(decodedSecret);
            this.base64Secret = secret;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
