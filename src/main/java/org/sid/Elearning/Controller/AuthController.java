//package org.sid.Elearning.Controller;
//
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
//
//@RestController
//public class AuthController {
//    private final AuthenticationManager authenticationManager;
//    private JwtEncoder jwtEncoder;
//    private JwtDecoder jwtDecoder;
//    private UserDetailsService userDetailsService;
//    public AuthController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, JwtDecoder jwtDecoder, UserDetailsService userDetailsService){
//        this.jwtEncoder=jwtEncoder;
//        this.authenticationManager=authenticationManager;
//        this.jwtDecoder = jwtDecoder;
//        this.userDetailsService = userDetailsService;
//    }
//    @PostMapping("/token")
//    public ResponseEntity<Map<String,String>> jwtoken(String username,
//                                                 String password,
//                                                 String grantType,
//                                                 boolean withrefreshToken,
//                                                 String refreshtoken) {
//
////        Authentication authentication=null;
//        String subject=null;
//        String role=null;
//        if(grantType.equals("password")){
//            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password)
//            );
//            subject=authentication.getName();
//            role=authentication.getAuthorities()
//                    .stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(""));
//
//        }else if (grantType.equals("refreshtoken")){
//            if(refreshtoken==null){
//                return new ResponseEntity<>( Map.of("msg","refreshToken is empty"),HttpStatus.UNAUTHORIZED);
//            }
//            Jwt decodeJWT= null;
//            try {
//                decodeJWT = jwtDecoder.decode(refreshtoken);
//            } catch (JwtException e) {
//                return new ResponseEntity<>( Map.of("msg",e.getMessage()),HttpStatus.UNAUTHORIZED);
//            }
//            subject=decodeJWT.getSubject();
//           UserDetails userDetails= userDetailsService.loadUserByUsername(subject);
//        Collection<? extends GrantedAuthority> authorities= userDetails.getAuthorities();
//         role=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(""));
//
//        }
//
//
//
//
//
//        Map<String, String> idtoken =new HashMap<>();
//        Instant instant = Instant.now();
//          JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                .subject(subject)
//                .issuedAt(instant)
//                .expiresAt(instant.plus(withrefreshToken?5:30, ChronoUnit.MINUTES))
//                .issuer("security-services")
//                .claim("role", role)
//                .build();
//        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
//        idtoken.put("accessToken", jwtAccessToken);
//        if(withrefreshToken){
//            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
//                    .subject(subject)
//                    .issuedAt(instant)
//                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
//                    .issuer("security-services")
//                    .claim("role", role)
//                    .build();
//            String jwtRefreshtoken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
//    idtoken.put("refreshtoken",jwtRefreshtoken);
//        }
//        return new ResponseEntity<>(idtoken,HttpStatus.OK);
//    }
//}
