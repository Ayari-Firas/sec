package org.sid.Elearning.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    private JwtTokenService jwtTokenService;
    @Autowired
    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        this.jwtTokenService = jwtTokenService;
//        this.jwtTokenService = jwtTokenService;
    }

    CustomUserDetailService customUserDetailService;

    @Override
//    @AuthenticationProvider(beanName = "loginFilterAuthenticationProvider")
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException, IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();

        Map<String, String> tokens = jwtTokenService.generateTokens (userDetails.getUsername(), null, "password", true);

        // Send tokens in the response (JSON format)
        response.setContentType("application/json");
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(tokens));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
