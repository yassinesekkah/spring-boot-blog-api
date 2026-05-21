package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class KeyConfig {


    @Value("${jwt.private-key-path}")
    private Resource privateKeyResource;


    @Value("${jwt.public-key-path}")
    private Resource publicKeyResource;

    @Bean
    public PrivateKey privateKey() throws Exception {
        String privateKeyContent = new String(
                privateKeyResource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        // Clean PEM format (remove headers/footers/newlines)
        String privateKeyPEM = privateKeyContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    @Bean
    public PublicKey publicKey() throws Exception {
        String publicKeyContent = new String(
                publicKeyResource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        String publicKeyPEM = publicKeyContent
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }
}