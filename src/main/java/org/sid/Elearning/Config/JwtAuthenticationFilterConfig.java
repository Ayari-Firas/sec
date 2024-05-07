//package org.sid.Elearning.Config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//
//@Configuration
//public class JwtAuthenticationFilterConfig  {
//    JwtAuthenticationFilter jwtAuthenticationFilter;
//    @Autowired
//    private CustomUserDetailService customUserDetailsService;
//
//    @Autowired
//    private rsakeysConfig rsakeysConfig;
//
//
//    private AuthenticationManager authenticationManager;
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(CustomUserDetailService customUserDetailService, AuthenticationManager authenticationManager) {
//        return new JwtAuthenticationFilter(customUserDetailsService, rsakeysConfig, authenticationManager);
//    }
//
//}
