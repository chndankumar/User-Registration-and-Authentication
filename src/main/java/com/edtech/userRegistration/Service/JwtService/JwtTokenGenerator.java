package com.edtech.userRegistration.Service.JwtService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtTokenGenerator {

    //@Value("${jwt.secret}")
    private final String privateKey = "oiweljbdeoiufeljrfboubferljibeirfbeirbf8ierfeifbjhsdfjhsew";

    //@Value("${jwt.expiration}")
    private final long jwtExpirationInMillis = 60;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("jti", UUID.randomUUID().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
                .signWith(getSigningKey())
                .compact();
    }
}
