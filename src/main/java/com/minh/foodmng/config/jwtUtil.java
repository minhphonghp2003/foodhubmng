package com.minh.foodmng.config;

import com.minh.foodmng.user.User;
import com.nimbusds.jose.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class jwtUtil {
    private String SECRET_KEY = "secret";
    KeyPairGenerator keyGenerator;

    {
        try {
            keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    KeyPair kp = keyGenerator.genKeyPair();
    PublicKey publicKey = (PublicKey) kp.getPublic();
    PrivateKey privateKey = (PrivateKey) kp.getPrivate();
    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);



    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractId(String token) {
        return extractClaim(token, Claims::getId);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User u) {
        Claims claims = Jwts.claims();
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).setIssuedAt(new Date(System.currentTimeMillis()))
                .setId(String.valueOf(u.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .compact();
    }

    public Boolean validateToken(String token ) {
        return (!isTokenExpired(token));
    }
}