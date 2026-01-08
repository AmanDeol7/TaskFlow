package com.demo.taskflow.auth;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private  final SecretKey JWT_SECRET ;
    private final long expiration;
    public JwtService(@Value("${jwt.secret}") String jwtSecret , @Value("${jwt.expiration}") long expiration) {
        this.JWT_SECRET = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;

    }

    public String generateToken(String email){
        return Jwts.builder().setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(JWT_SECRET, SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();


    }

}
