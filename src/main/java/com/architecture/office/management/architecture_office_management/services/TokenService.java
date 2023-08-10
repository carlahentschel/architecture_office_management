package com.architecture.office.management.architecture_office_management.services;

import com.architecture.office.management.architecture_office_management.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {
    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256("chave_secreta");
            return JWT.create()
                    .withIssuer("architecture-office-management.com")
                    .withSubject(user.getId().toString())
                    .withClaim("name", user.getUsername())
                    .withExpiresAt(setExpirationTime())
                    .sign(algorithm);
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Error ao gerar token");
        }
    }

    public UUID getSubject(String token) {
        var algorithm = Algorithm.HMAC256("chave_secreta");
        var jwtDecode = JWT.require(algorithm).withIssuer("architecture-office-management.com").build();
        var jwt = jwtDecode.verify(token);
        return UUID.fromString(jwt.getSubject());
    }

    private Instant setExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
