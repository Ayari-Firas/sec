package org.sid.Elearning.Config;


import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.management.remote.JMXAuthenticator;
import java.io.IOException;
import java.security.PublicKey;
import java.text.ParseException;


@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    private CustomUserDetailService customUserDetailService;
//    private  AuthenticationManager authenticationManager;
//    private rsakeysConfig publickey;
//
//    @Autowired
//    public JwtAuthenticationFilter(CustomUserDetailService customUserDetailService, rsakeysConfig publicKey, AuthenticationManager authenticationManager) {
//        //  super(String.valueOf(new JwtAuthenticationProvider(null))); // Don't provide a JWTParser here
//        this.customUserDetailService = customUserDetailService;
//        this.publickey = publicKey;
//        this.authenticationManager = authenticationManager;
//    }

//    @Autowired
//    public void setCustomUserDetailsService(CustomUserDetailService customUserDetailsService) {
//        this.customUserDetailService = customUserDetailsService;
//    }
//
//    @Autowired
//    public void setJwtDecoder(JwtDecoder jwtDecoder) {
//        this.jwtDecoder = jwtDecoder;
//    }
//
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

//    @Autowired
//    public JwtAuthenticationFilter(CustomUserDetailService customUserDetailService, rsakeysConfig rsakeysConfig, AuthenticationManager authenticationManager) {
//        this.customUserDetailService = customUserDetailService;
//        this.rsakeysConfig = rsakeysConfig;
//        this.authenticationManager = authenticationManager;
//    }


private PublicKey publicKey;
        private CustomUserDetails customUserDetails;
//        private rsakeysConfig publicKey;
        private CustomUserDetailService customUserDetailService;
        private AuthenticationManager authenticationManager;

    // Autowire by type


    @Autowired
        public JwtAuthenticationFilter(CustomUserDetailService customUserDetailService) {
            this.customUserDetailService = customUserDetailService;
//            this.publicKey = publicKey;
        }

        // ... rest of your filter logic ...
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailService CustomUserDetailService){
        var authProvider=new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(CustomUserDetailService);
        return new ProviderManager(authProvider);
    }



        private String resolveToken(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        }


        @Override
        public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = resolveToken(request); // Extract JWT token from request (e.g., Authorization header)
            if (token != null) {
                try {
                    String tokenString = JWTParser.parse(token).toString(); // Get the complete JWT string
                    JWTClaimsSet claims = JWTParser.parse(token).getJWTClaimsSet();
                    Jwt jwt = (Jwt) JWTParser.parse(token);
                    String username = claims.getSubject();
                    CustomUserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
                    JwtAuthenticationToken authentication;
                    authentication = new JwtAuthenticationToken(jwt, userDetails.getAuthorities());
                     authenticationManager.authenticate(authentication); // Use the injected AuthenticationManager
                } catch (JwtException e) {
                    throw new AuthenticationServiceException("Invalid JWT token"); // Handle invalid JWT
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            filterChain.doFilter(request, response); // Proceed with the filter chain
        }
        }




//@Component
//public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    private final UserDetailsService userDetailsService;
//    private final JWTParser jwtParser;
//
//    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JWTParser jwtParser) {
//        super(new JwtAuthenticationProvider(jwtParser)); // Use JwtAuthenticationProvider for JWT processing
//        this.userDetailsService = userDetailsService;
//        this.jwtParser = jwtParser;
//    }
//
//    @Override
//    protected Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String token = resolveToken(request); // Extract JWT token from request (e.g., Authorization header)
//        if (token != null) {
//            try {
//                JWTClaimsSet claims = jwtParser.parseClaimsJws(token).getBody(); // Parse and validate JWT token
//                String username = claims.getSubject();
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                JwtAuthenticationToken authentication = new JwtAuthenticationToken(claims, null, userDetails.getAuthorities());
//                return getAuthenticationManager().authenticate(authentication);
//            } catch (JwtException e) {
//                throw new AuthenticationServiceException("Invalid JWT token"); // Handle invalid JWT
//            }
//        }
//
//        return null;
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}

