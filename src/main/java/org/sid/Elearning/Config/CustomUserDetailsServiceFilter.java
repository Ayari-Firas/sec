//package org.sid.Elearning.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
//import org.springframework.web.filter.GenericFilterBean;
//
//import java.io.IOException;
//
//public class CustomUserDetailsServiceFilter extends GenericFilterBean {
//
//    private final CustomUserDetailService customUserDetailService;
//
//    @Autowired
//    public CustomUserDetailsServiceFilter(CustomUserDetailService customUserDetailService) {
//        this.customUserDetailService = customUserDetailService;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String username = httpRequest.getHeader("username"); // Supposons que le nom d'utilisateur soit passé dans l'en-tête "username"
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            CustomUserDetails userDetails = customUserDetailService.loadUserByUsername(username);
//            if (userDetails != null) {
//                Authentication authentication = new PreAuthenticatedAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//
//
//}
