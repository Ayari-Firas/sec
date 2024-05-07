package org.sid.Elearning.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.Elearning.Enum.Role;
import org.sid.Elearning.Repository.UserRepo;
import org.sid.Elearning.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public final class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepo userRepository;

    @Override

    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByFullname(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        log.info("User found: {}", user);

        String password = user.get().getPassword();
        List<GrantedAuthority> roles = new ArrayList<>();

        if (user.get().hasRole(Role.ROLE_ADMIN)) {
            roles.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
        } else if (user.get().isFormateur()) {
            roles.add(new SimpleGrantedAuthority(Role.ROLE_FORMATEUR.toString()));
        } else if (user.get().getFormations().stream().anyMatch(id -> id.isPremiumFormation())) {
            // Persist the role if necessary (consider user management service)
            if (user.get().isPersistRoleChanges()) { // Extract the User object
                user.get().addRole(Role.ROLE_USERPREMIUM);
                userRepository.save(user.get());
            }
            roles.add(new SimpleGrantedAuthority(Role.ROLE_USERPREMIUM.toString()));
        } else {
            roles.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString())); // Default role
        }


        return new CustomUserDetails(username, password, roles);
    }
}

//private final String ROLE_PEFIX="";

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user= this.userRepo.findByUsername(username);
//        user.orElseThrow(()->new UsernameNotFoundException("user not Found"));
//        log.info("user {}",user.get());
//
//        String password=user.get().getPassword();
//        String role= user.get().getRole().toString();
//        role=ROLE_PEFIX+role;
//        List<GrantedAuthority>roles=new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority(role));
//
//        return new CustomUserDetails(username,password,roles);
//    }

//    if (user.get().hasRole(Role.ROLE_ADMIN)) {
//        roles.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
//    } else {
//        if (user.get().getFormations().stream().anyMatch(id -> id.isPremiumFormation())) {
//            user.get().addRole(Role.ROLE_USERPREMIUM); // Add role if applicable (avoid modifying retrieved user object)
//            roles.add(new SimpleGrantedAuthority(Role.ROLE_USERPREMIUM.toString()));
//        }
//
//        if (user.get().isFormateur()) {
//            roles.add(new SimpleGrantedAuthority(Role.ROLE_FORMATEUR.toString()));
//        } else {
//            roles.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString())); // Default role for non-admins
//        }
//    }


