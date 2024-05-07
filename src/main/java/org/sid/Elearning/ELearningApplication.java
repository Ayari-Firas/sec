package org.sid.Elearning;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.sid.Elearning.Repository.FormationRepo;
import org.sid.Elearning.Repository.UserRepo;
import org.sid.Elearning.entities.Formation;
import org.sid.Elearning.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
@OpenAPIDefinition
public class ELearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearningApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner insertData(UserRepo userRepo, FormationRepo formationRepo) {
//        return args -> {
//            // Create a new formation
//            Formation formation = new Formation();
//            formation.setName("Spring Security Essentials");
//            formation.setContenu("Learn how to secure your Spring applications...");
//            formation.setDuree("2 hours");
//            formation.setType("Online Course");
//            formation.setDescription("A comprehensive guide to Spring Security...");
//            formation.setDateDeb(new Date());
//            formation.isPremiumFormation();// Set current date or desired start date
//            // Add other formation properties as needed
//
//            // Save the formation first
//            formationRepo.save(formation);
//
//            // Create a new user
//            User user = new User();
//            user.setFullname("Firas");
//            user.setEmail("john@example.com");
//            user.setPassword("ayari");
//            user.setRoles(user.getRoles());
//
//            // Add the saved formation to the user's formations list
//            user.getFormations().add(formation);
//
//            // Save the user with the associated formation (formation has an ID now)
//            userRepo.save(user);
//        };
//    }
}
