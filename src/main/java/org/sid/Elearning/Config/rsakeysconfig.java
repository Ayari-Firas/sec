//package org.sid.Elearning.Config;
//
////import com.nimbusds.jose.util.Resource;
//import com.nimbusds.jose.crypto.RSASSAVerifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.*;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//
//import org.springframework.core.io.Resource;
//
//import java.io.InputStream;
//import java.security.Key;
//import java.security.KeyFactory;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
//@ConfigurationProperties(prefix = "rsa")
//
//public class rsakeysconfig {
//
//
//        private final ResourceLoader resourceLoader=null;
//        private String publicKeyPath;
//        private String privateKeyPath;
//
//
//
//        @Value("${rsa.publickey}")
//        public void setPublicKeyPath(String publicKeyPath) {
//            this.publicKeyPath = publicKeyPath;
//        }
//
//        @Value("${rsa.privatekey}")
//        public void setPrivateKeyPath(String privateKeyPath) {
//            this.privateKeyPath = privateKeyPath;
//        }
//
//
//        public RSAPublicKey publicKey() throws Exception {
//            try{
//                Key key = loadKey("PUBLIC", publicKeyPath);
//
//
//            if (key instanceof RSAPublicKey) {
//                return (RSAPublicKey) key;
//            } else {
//                throw new IllegalStateException("Unexpected key type for public key: " + key.getClass().getName());
//            } }catch (Exception e) {
//                // Handle the exception appropriately (e.g., log the error, throw a specific exception)
//                throw new RuntimeException("Error loading public key", e); // Example handling
//            }
//        }
//
//
//    public RSAPublicKey privateKey() throws Exception {
////        return SecurityContextHolder.getContext().getAuthentication() != null &&
////                SecurityContextHolder.getContext().getAuthentication().hasAuthority("ROLE_ADMIN") ?
////                (RSAPublicKey) loadKey("PRIVATE", privateKeyPath) : null;
////    }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated() &&
//                authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
//            return  (RSAPublicKey) loadKey("PRIVATE", privateKeyPath) ;
//
//        } else {
//            return null;
//        }}
//
//        private Key loadKey(String keyType, String filePath) throws Exception {
//            Resource resource = resourceLoader.getResource(filePath);
//
//            try (InputStream inputStream = resourceLoader.getResource(filePath).getInputStream()) {
//                byte[] keyBytes = inputStream.readAllBytes();
//
//                // Use KeyFactory to parse the PEM encoded key data
//                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//                PKCS8EncodedKeySpec privateKeySpec = (keyType.equals("PRIVATE")) ?
//                        new PKCS8EncodedKeySpec(keyBytes) : null;
//                X509EncodedKeySpec publicKeySpec = (keyType.equals("PUBLIC")) ?
//                        new X509EncodedKeySpec(keyBytes) : null;
//
//                return keyFactory.generatePublic(publicKeySpec); // or privateKey for private key
//            }
//        }
//    }
//
//
