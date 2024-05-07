//package org.sid.Elearning.Config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//import java.io.IOException;
//import java.util.Map;
//
//public class AuthFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final AuthService authService=null;
//    CustomUserDetailService customUserDetailService;
//    rsakeysConfig publicKey;
//
//    @Autowired
//    public AuthFilter(CustomUserDetailService customUserDetailService, rsakeysConfig publicKey, AuthenticationManager authenticationManager) {
//        this.customUserDetailService = customUserDetailService;
//        this.publicKey = publicKey;
//        this.authenticationManager = authenticationManager;
//    }
////    public AuthFilter(AuthenticationManager authenticationManager, AuthService authService) {
////        this.authenticationManager = authenticationManager;
////        this.authService = authService;
////    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String username = ((CustomUserDetails) authResult.getPrincipal()).getUsername();
//        String password = request.getParameter("password");
//        String grantType = "password";
//
//        Map<String, String> tokens = authService.generateTokens(username, password, grantType, true);
//
//        response.setContentType("application/json");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(tokens));
//    }
//}
//
