package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "bXlzZWNyZXRrZXlteXNlY3JldGtleW15c2VjcmV0a2V5MTI=";

    //transfer sercret key man string l key object
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        System.out.println("keyBytes: " + Arrays.toString(keyBytes));

        Key key = Keys.hmacShaKeyFor(keyBytes);
//        System.out.println(key);
        return key;
    }

    public String generateToken(String email){

        System.out.println("Building JWT...");

        Date issuedAt = new Date();
        System.out.println("Issued At: " + issuedAt);

        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
        System.out.println("Experation: " + expirationDate);

        //jwts.biulder t9ol kan9olo jwt start building
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)

                .signWith(getSignInKey(), SignatureAlgorithm.HS256)

                .compact();

        System.out.println("Generated Token: " + token);

        return token;
    }

    public String extractEmail(String token){

        System.out.println("Received Token:");
        System.out.println(token);

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build();
        System.out.println("Parser Created" + parser);

        Jws<Claims> jwsClaims = parser.parseClaimsJws(token);
        System.out.println("Token Parsed " + jwsClaims);

        Claims claims = jwsClaims.getBody();
        System.out.println("Claims: " + claims);

        String email = claims.getSubject();
        System.out.println("Extracted Email: " + email);

        return email;
    }

}
