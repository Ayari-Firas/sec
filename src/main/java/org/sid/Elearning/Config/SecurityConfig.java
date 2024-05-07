//package org.sid.Elearning.Config;
//
//import com.nimbusds.jose.jwk.JWK;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
//import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.RSASSASigner;
//import com.nimbusds.jose.crypto.RSASSAVerifier;
//import com.nimbusds.jose.jwk.JWK;
//
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.JWK;
//import com.nimbusds.jose.jwk.JWKSet;
//
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.X509EncodedKeySpec;

//@Configuration
//@EnableWebSecurity
//@EnableConfigurationProperties(rsakeysConfig.class)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig  extends WebSecurityConfiguration {
//    private PasswordEncoder passwordEncoder;
//    private rsakeysConfig rsakeysConfig;
//    private CustomUserDetailService userDetailsService;
//    private AuthenticationManager authenticationManager;
//    private JwtDecoder jwtDecoder;
//    private JwtEncoder jwtEncoder;
//    @Autowired
//    private AuthService authService;
////    CustomUserDetails customUserDetails;
////
////
////    CustomUserDetailService customUserDetailService;
////    @Autowired
////    @Lazy
////    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(rsakeysConfig rsakeysConfig){
//    this.rsakeysConfig=rsakeysConfig;
////    this.passwordEncoder=passwordEncoder;
////    this.customUserDetails = customUserDetails;
////    this.authenticationManager = authenticationManager;
//}
////@Bean
////public AuthenticationManager authenticationManager(CustomUserDetailService customUserDetailService){
////    var authProvider=new DaoAuthenticationProvider();
////    authProvider.setPasswordEncoder(passwordEncoder);
////    authProvider.setUserDetailsService(customUserDetailService);
////    return new ProviderManager(authProvider);
////}
//@Bean
//public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//}
//@Bean
//public AuthFilter authFilter() throws Exception {
//    AuthFilter filter = new AuthFilter(userDetailsService, rsakeysConfig,authenticationManager);
//    filter.setFilterProcessesUrl("/api/auth");
//    return filter;
//}
//public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
//
//    return httpSecurity
//            .csrf(csrf->csrf.disable())
//            .authorizeHttpRequests(auth->auth.requestMatchers("/*").permitAll())
//            .authorizeRequests(auth->auth.anyRequest().authenticated())
//            .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////            .addFilterBefore(new CustomUserDetailsServiceFilter(customUserDetailService), JwtAuthenticationFilter.class)
//            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
////            .addFilterBefore(new JwtAuthenticationFilter(customUserDetailService, rsakeysConfig), JwtAuthenticationFilter.class)
//            .build();
//
//
//
//
////}
//    @Bean
//    public JwtEncoder jwtEncoder(){
//        JWK jwk=new RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
//        JWKSource<SecurityContext> jWkSource =new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jWkSource);
//    }
//
//
//    @Bean
//    public JwtDecoder jwtDecoder(){
//        return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(CustomUserDetailService CustomUserDetailService){
//        var authProvider=new DaoAuthenticationProvider();
//        authProvider.setPasswordEncoder(passwordEncoder());
//        authProvider.setUserDetailsService(CustomUserDetailService);
//        return new ProviderManager(authProvider);
//    }
////    @Bean
////    public CustomUserDetailService customUserDetails(String username, String password, List<GrantedAuthority> roles) {
////        // Instanciation et configuration de CustomUserDetails si nécessaire
////        return new CustomUserDetailService(adminrep adminrep);
////    }
//
//
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailService userDetailsService) {
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setPasswordEncoder(passwordEncoder());
////        provider.setUserDetailsService(userDetailsService);
////        return provider;
////    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//
//
//
//}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////
////    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
////        http.authorizeHttpRequests(authrequest->{
////            authrequest.anyRequest().authenticated();
////            authrequest.requestMatchers("/login","/register","/about","/home","api/formations").permitAll();
////           authrequest.requestMatchers("/**").hasRole("ADMIN");
////          //  authrequest.requestMatchers("/jqdskh").hasAnyRole("hgdsjh","qhsdhg");
////
////
////
////
////        });
////        http.cors(AbstractHttpConfigurer::disable);
////        http.csrf(AbstractHttpConfigurer::disable);
////        http.headers(AbstractHttpConfigurer::disable);
////        http.formLogin(Customizer.withDefaults());
////        http.httpBasic(Customizer.withDefaults());
////
////
////        return http.build();
////
////    }
//
//
////    @Bean
////    public JwtAuthenticationFilter jwtAuthenticationFilter(UserDetailsService userDetailsService, String publicKeyString) throws Exception {
////        // Replace with your actual public key in PEM format (base64 encoded)
////        RSAPublicKey publicKey = RSAKeyUtils.parsePublicKeyFromPemEncodedString(publicKeyString);
////
////        return new JwtAuthenticationFilter(userDetailsService, publicKey);
////    }
////@Bean
////public JwtAuthenticationFilter jwtAuthenticationFilter(UserDetailsService userDetailsService, String publicKeyString) throws Exception {
////    // Parse the public key from PEM format
////    RSAPublicKey publicKey = RSAKeyUtils.parsePublicKeyFromPemEncodedString(publicKeyString);
////
////    // Create a JWTParser using the public key
////    JWTParser jwtParser = Jwts.builder().setSigningKey(publicKey).build();
////
////    return new JwtAuthenticationFilter(userDetailsService, jwtParser);
////}
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder(); // Example using BCryptPasswordEncoder
////    }
////    @Bean
////    public String publicKey() {
////        // Load the public key from a secure location (replace with your actual mechanism)
////        String publicKeyString = "YOUR_PUBLIC_KEY_IN_PEM_FORMAT"; // Placeholder
////        return publicKeyString;
////    }
////    private static String loadPublicKey() throws Exception {
////        // Implement logic to load the public key from a secure location (e.g., resource file, keystore)
////        // Avoid hardcoding the public key in your code
////        throw new UnsupportedOperationException("Implement secure public key loading");
////    }
////    @Bean
////    public JwtAuthenticationFilter createJwtAuthenticationFilter(UserDetailsService userDetailsService) throws Exception {
////        // Replace with your actual public key in PEM format (base64 encoded) loaded securely
////        String publicKeyString = loadPublicKey(); // Placeholder for secure key loading
////
////        // Use Spring Security JWT library to parse and validate JWT token
////        JWTParser jwtParser = JwtClaimsSet.builder().setSigningKey(Keys.hmacShaKeyFor(publicKeyString.getBytes())).build();
////
////        return new JwtAuthenticationFilter(userDetailsService, jwtParser) {
////            @Override
////            protected Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
////                String token = resolveToken(request); // Extract JWT token from request (e.g., Authorization header)
////                if (token != null) {
////                    try {
////                        // Parse and validate JWT token
////                        JwtClaimsSet claims = jwtParser.parseClaims(token).getBody();
////
////                        // Validate token expiration
////                        if (claims.getExpirationTime().before(new Date())) {
////                            throw new JwtException("Token is expired");
////                        }
////
////                        // Extract user information from token claims
////                        String username = claims.getSubject();
////
////                        // Load user details from the database
////                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////
////                        // Create JwtAuthenticationToken and return for authentication
////                        return new JwtAuthenticationToken(claims, null, userDetails.getAuthorities());
////
////                    } catch (JwtException e) {
////                        throw new AuthenticationServiceException("Invalid JWT token: " + e.getMessage()); // Handle invalid JWT
////                    }
////                }
////
////                return null;
////            }
////
////            private String resolveToken(HttpServletRequest request) {
////                String bearerToken = request.getHeader("Authorization");
////                if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
////                    return bearerToken.substring(7);
////                }
////                return null;
////            }
////        };
////    }
//
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests(auth -> auth{
////                        .requestMatchers("/login", "/register", "/about", "/home", "/api/formations").permitAll()
////                        .anyRequest().authenticated())
////                .cors().disable() // Consider configuring for specific origins in production
////                .csrf().disable() // May need to be enabled later
////                .headers().contentTypeOptions().disable() // Consider using a Content Security Policy (CSP)
////                .and()
////                .addFilterBefore(createJwtAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class) // Replace with actual filter creation
////                .formLogin(Customizer.withDefaults())
////                .httpBasic(Customizer.withDefaults());
////        }
////        return http.build();
////    }





//
//    PublicKey publicKey;
//
//    {
//        try {
//            publicKey = rsaKeysConfig.getPublicKey();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    PrivateKey privateKey;
//
//    {
//        try {
//            privateKey = rsaKeysConfig.getPrivateKey();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @Bean
//    public JwtEncoder jwtEncoder() throws Exception {
//        JWK jwk = new RSAKey((RSAPublicKey) rsaKeysConfig.getPublicKey(), (java.security.PrivateKey) rsaKeysConfig.getPrivateKey()).toPublicJWK();
//        JWKSource<SecurityContext> jWkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
//
//        return new NimbusJwtEncoder(jWkSource);
//    }
//
//
//    @Bean
//    public JwtDecoder jwtDecoder(){
//        return NimbusJwtDecoder.withPublicKey((RSAPublicKey)rsaKeysConfig.getPublicKey()).build();
//    }

//    @Bean
//    public JwtEncoder jwtEncoder(RSAPrivateKey privateKey) throws Exception {
//        RSASSASigner signer = new RSASSASigner((java.security.PrivateKey)privateKey);
//        return new JwtEncoder(new JwsAlgorithm(JwsAlgorithms.RS256), signer);
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder(RSAPublicKey publicKey) throws Exception {
//        RSASSAVerifier verifier = new RSASSAVerifier((java.security.interfaces.RSAPublicKey) publicKey);
//        return new JwtDecoder(new JwsAlgorithm(JwsAlgorithms.RS256), verifier);
//    }

package org.sid.Elearning.Config;

import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.RSAKey;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.springframework.stereotype.Component;


//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


@Configuration
@EnableWebSecurity

//@EnableGlobalMethodSecurity(prePostEnabled = true)


public class SecurityConfig extends WebSecurityConfiguration {


    private PasswordEncoder passwordEncoder;


    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;


    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    // Assuming you've already loaded the RSA keys




//    @Bean
//
//    public JwtTokenService jwtTokenService() {
//        return new JwtTokenService();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
////    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()  // Disable CSRF for stateless JWT authentication
//                .authorizeRequests(authrequest -> {
//
//                    authrequest.anyRequest().authenticated();
//                    authrequest.requestMatchers("/login", "/register", "/about", "/home", "api/formations").permitAll();
//                    authrequest.requestMatchers("/**").hasRole("ADMIN");
//                    authrequest.requestMatchers("/login").permitAll();  // Allow login endpoint without authentication
//                })
//
////                .addFilterAfter(new LoginFilter(authenticationManager, jwtTokenService),UsernamePasswordAuthenticationFilter.class)  // Use LoginFilter before default
////                .addFilterAfter(new JwtTokenAuthenticationFilter(authenticationManager, jwtDecoder), // Assuming you have JwtTokenAuthenticationFilter implemented
////                        LoginFilter.class)  // Add JWT token filter
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();    }


    //    @Bean
//    public RSASSAVerifier rsaVerifier() throws Exception {
//        return new RSASSAVerifier((java.security.interfaces.RSAPublicKey) rsaKeysConfig.getPublicKey());
//    }
//    @Bean
//    public RSASSAVerifier rsaVerifier() throws Exception {
//
//        return new RSASSAVerifier((java.security.interfaces.RSAPublicKey) publicKey);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public JwtEncoder jwtEncoder() throws Exception {
////       JWK jwk = new RSAKey.Builder( rsaKeysConfig.publicKey(), rsaKeysConfig.privateKey()).toPublicJWK();
//        RSAKey rsaKey = new RSAKey.Builder((java.security.interfaces.RSAPublicKey) publicKey)
//                .privateKey((java.security.interfaces.RSAPrivateKey) privateKey)
//                .build();
//        JWK jwk = rsaKey.toPublicJWK();
//        JWKSource<SecurityContext> jWkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
//
//        return new NimbusJwtEncoder(jWkSource);
//    }
//
//
//    @Bean
//    public JwtDecoder jwtDecoder(){
////        return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
////        RSAPublicKey publicKey = rsaKeysConfig.publicKey(); // Assuming publicKey handles exceptions
//        try {
//            if (publicKey != null) {
//                return NimbusJwtDecoder.withPublicKey((java.security.interfaces.RSAPublicKey) publicKey).build();
//            } else {
//                // Handle the case where the public key is not available (e.g., log an error)
//                throw new RuntimeException("Public key not available for JWT decoding");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
@Override
protected void configure(HttpSecurity http) throws Exception {
   http .csrf().disable()  // Disable CSRF for stateless JWT authentication
                .authorizeRequests(authrequest -> {

                    authrequest.anyRequest().authenticated();
                    authrequest.requestMatchers("/login", "/register", "/about", "/home", "api/formations").permitAll();
                    authrequest.requestMatchers("/**").hasRole("ADMIN");
                    authrequest.requestMatchers("/login").permitAll();  // Allow login endpoint without authentication
                })

//                .addFilterAfter(new LoginFilter(authenticationManager, jwtTokenService),UsernamePasswordAuthenticationFilter.class)  // Use LoginFilter before default
//                .addFilterAfter(new JwtTokenAuthenticationFilter(authenticationManager, jwtDecoder), // Assuming you have JwtTokenAuthenticationFilter implemented
//                        LoginFilter.class)  // Add JWT token filter
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         http.build();    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}

