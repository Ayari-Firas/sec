//package org.sid.Elearning.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import java.io.IOException;
//
//public class JwtTokenAuthenticationFilter extends GenericFilterBean {
//
//    private final JwtDecoder jwtDecoder;
//
//    AuthenticationManager authenticationManager;
//
//    public JwtTokenAuthenticationFilter(JwtDecoder jwtDecoder) {
//        this.jwtDecoder = jwtDecoder;
//    }
////@Autowired
////    public JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager, JwtDecoder jwtDecoder) {
//////        super();
////        this.jwtDecoder = jwtDecoder;
////    }
//
////    @Override
////    protected Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
////        String token = request.getHeader("Authorization");
////
////        if (token != null && token.startsWith("Bearer ")) {
////            String jwtToken = token.substring(7);
////            Jwt decodedToken = jwtDecoder.decode(jwtToken);
////        }
////    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//
//        String token = httpRequest.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            String jwtToken = token.substring(7);
//            Jwt decodedToken = jwtDecoder.decode(jwtToken);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//
//    }
//}