package org.sid.Elearning.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//public class JwtTokenService {
//@Autowired
//    private final JwtEncoder jwtEncoder;
//@Autowired
//    private final JwtDecoder jwtDecoder;
//    @Autowired
//    private final CustomUserDetailService userDetailsService;
//
//AuthenticationManager authenticationManager;
public class JwtTokenService {

    private JwtEncoder jwtEncoder;

    private JwtDecoder jwtDecoder;

    private CustomUserDetailService userDetailsService;
    private AuthenticationManager authenticationManager;

//    private SecurityConfig securityConfig;

//
//    @Autowired
//    public void setJwtEncoder(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    @Autowired
//    public void setJwtDecoder(JwtDecoder jwtDecoder) {
//        this.jwtDecoder = jwtDecoder;
//    }
//
//    @Autowired
//    public void setUserDetailsService(CustomUserDetailService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    // Your existing methods here...




    public  Map<String, String> generateTokens(String username, String refreshToken, String grantType, boolean withRefreshToken) {
        Map<String, String> tokens = new HashMap<>();

        if (grantType.equals("password")) {
            Authentication authentication = authenticate(username, null);
            String subject = authentication.getName();
            Authentication custom= (Authentication) userDetailsService.loadUserByUsername(subject);
            String role = extractUserRole(custom);

            tokens = createAndEncodeTokens(subject, role, withRefreshToken);
        } else if (grantType.equals("refreshtoken")) {
            String refreshToke = username; // Assuming username holds the refresh token
            tokens = refreshAccessToken(refreshToke);
            throw new IllegalArgumentException("Unsupported grant type: " + grantType);
        }

        return tokens;
    }
    private Map<String, String> refreshAccessToken(String refreshToken) {
        try {
            Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken); // Decoded JWT token
            String subject = decodedRefreshToken.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            String role = extractUserRole((Authentication) userDetails.getAuthorities());
            JwtAuthenticationToken authentication = new JwtAuthenticationToken(decodedRefreshToken, userDetails.getAuthorities());  // Pass the decoded JWT token
            authenticationManager.authenticate(authentication); // Use the injected AuthenticationManager
            return createAndEncodeTokens(subject, role, true); // Always generate refresh token with refresh request
        } catch (JwtException e) {
            throw new AuthenticationServiceException("Invalid refresh token");
        }
    }


    private Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private String extractUserRole(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(""));
    }

    private Map<String, String> createAndEncodeTokens(String subject, String role, boolean withRefreshToken) {
        Map<String, String> tokens = new HashMap<>();

        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plus(withRefreshToken ? 5 : 30, ChronoUnit.MINUTES)) // Adjust durations as needed
                .issuer("security-services")
                .claim("role", role)
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        tokens.put("accessToken", accessToken);

        if (withRefreshToken) {
            JwtClaimsSet refreshTokenClaimsSet = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(now)
                    .expiresAt(now.plus(30, ChronoUnit.MINUTES)) // Adjust duration as needed
                    .issuer("security-services")
                    .claim("role", role)
                    .build();

            String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaimsSet)).getTokenValue();
            tokens.put("refreshtoken", refreshToken);
        }else {
            throw new IllegalArgumentException("Unsupported grant type: " );
        }

        return tokens;
    }
    public boolean verifyAccessToken(String accessToken) {
        try {
            jwtDecoder.decode(accessToken);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsernameFromAccessToken(String accessToken) {
        try {
            Jwt decodedToken = jwtDecoder.decode(accessToken);
            return decodedToken.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
