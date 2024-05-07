//package org.sid.Elearning.Config;
//
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Service;
//import org.sid.Elearning.Enum.Role;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//@Service
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtEncoder jwtEncoder;
//    private final JwtDecoder jwtDecoder;
//    private final UserDetailsService userDetailsService;
//
//    public AuthService(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, UserDetailsService userDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtEncoder = jwtEncoder;
//        this.jwtDecoder = jwtDecoder;
//        this.userDetailsService = userDetailsService;
//    }
//
//
//
//    public Map<String, String> generateTokens(String username, String password, String grantType, boolean withRefreshToken) {
//        Map<String, String> tokens = new HashMap<>();
//
//        if (grantType.equals("password")) {
//            Authentication authentication = authenticate(username, password);
//            String subject = authentication.getName();
//            String role = extractUserRole(authentication);
//            tokens = createAndEncodeTokens(subject, role, withRefreshToken);
//        } else if (grantType.equals("refreshtoken")) {
//            String refreshToken = username; // Assuming username holds the refresh token
//            tokens = refreshAccessToken(refreshToken);
//        } else {
//            throw new IllegalArgumentException("Unsupported grant type: " + grantType);
//        }
//
//        return tokens;
//    }
//
//    private Authentication authenticate(String username, String password) {
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    }
//
//    private String extractUserRole(Authentication authentication) {
//        return authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(""));
//    }
//
//    private Map<String, String> createAndEncodeTokens(String subject, String role, boolean withRefreshToken) {
//        Map<String, String> tokens = new HashMap<>();
//
//        Instant now = Instant.now();
//        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                .subject(subject)
//                .issuedAt(now)
//                .expiresAt(now.plus(withRefreshToken ? 5 : 30, ChronoUnit.MINUTES)) // Adjust durations as needed
//                .issuer("security-services")
//                .claim("role", role)
//                .build();
//
//        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
//        tokens.put("accessToken", accessToken);
//
//        if (withRefreshToken) {
//            JwtClaimsSet refreshTokenClaimsSet = JwtClaimsSet.builder()
//                    .subject(subject)
//                    .issuedAt(now)
//                    .expiresAt(now.plus(30, ChronoUnit.MINUTES)) // Adjust duration as needed
//                    .issuer("security-services")
//                    .claim("role", role)
//                    .build();
//
//            String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaimsSet)).getTokenValue();
//            tokens.put("refreshtoken", refreshToken);
//        }else {
//            throw new IllegalArgumentException("Unsupported grant type: " );
//        }
//
//        return tokens;
//    }
//    private Map<String, String> refreshAccessToken(String refreshToken) {
//        try {
//            Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken); // Decoded JWT token
//            String subject = decodedRefreshToken.getSubject();
//            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
//            String role = extractUserRole((Authentication) userDetails.getAuthorities());
//            JwtAuthenticationToken authentication = new JwtAuthenticationToken(decodedRefreshToken, userDetails.getAuthorities());  // Pass the decoded JWT token
//            authenticationManager.authenticate(authentication); // Use the injected AuthenticationManager
//            return createAndEncodeTokens(subject, role, true); // Always generate refresh token with refresh request
//        } catch (JwtException e) {
//            throw new AuthenticationServiceException("Invalid refresh token");
//        }
//    }
//
//}
//
////    private Map<String, String> refreshAccessToken(String refreshToken) {
////        try {
////            Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken);
////            String subject = decodedRefreshToken.getSubject();
////            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
////            String role = extractUserRole(userDetails);
////            JwtAuthenticationToken authentication = new JwtAuthenticationToken(decodedRefreshToken, userDetails.getAuthorities());  // Pass the decoded JWT token
////            authenticationManager.authenticate(authentication); // Use the injected AuthenticationManager
////
////            return createAndEncodeTokens(subject, role, true); // Always generate refresh token with refresh request
////        } catch (JwtException e) {
////            throw new AuthenticationServiceException("Invalid refresh token");
////        }
////    }
//
