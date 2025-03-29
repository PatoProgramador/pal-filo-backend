package com.palfilo.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.palfilo.demo.models.Users;
import jakarta.annotation.PostConstruct;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    private JWTVerifier verifier;

    @PostConstruct
    public void init() {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        this.verifier = JWT.require(algorithm)
                .withIssuer("palfilo")
                .build();
    }

    public String generateToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("palfilo")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getUserId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
            throw new RuntimeException("Token verification failed");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("-05:00"));
    }
}
