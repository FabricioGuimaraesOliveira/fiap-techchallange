package com.fiap.greentracefood.infrastructure.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    private final String secret;

    @Autowired
    public JwtDecoder(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String decodeAndExtractCPF(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("cpf").asString();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
