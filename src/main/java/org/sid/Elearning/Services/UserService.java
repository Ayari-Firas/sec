package org.sid.Elearning.Services;

import org.sid.Elearning.DTO.UserDTO;
import org.sid.Elearning.Repository.UserRepo;
import org.sid.Elearning.entities.FormateurDetails;
import org.sid.Elearning.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    /* -------------- Crud USER ------------*/
    public List<User> findAllUsers() {
        return userRepository.findAll(); // Assuming findAll is available in UserRepo
    }

    public UserDTO getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return toUserDto(user); // Convert User to UserDTO using toUserDto function
    }

//    public UserDTO getUserById(String userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isEmpty()) {
//            throw new NoSuchElementException("User not found with id: " + userId);
//        }
//        return toUserDto(userOptional.get());
//    }

    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId).orElseThrow();
        existingUser.setFullname(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setDatenaissance(userDTO.getDateNaissance());
        existingUser.setDiplome(userDTO.getDiplome());

        // Update formateur information if present in DTO
        // Update formateur information if present in DTO
        if (userDTO.getSpecialite() != null) {
            FormateurDetails formateurDetails = new FormateurDetails();
            formateurDetails.setSpecialite(userDTO.getSpecialite());
            formateurDetails.setUrlCv(userDTO.getUrlCv());
            formateurDetails.setDateDeb(userDTO.getDateDebFormateur());
            existingUser.setFormateurDetails(formateurDetails);
        }

        User user = userRepository.save(existingUser);
        return toUserDto(user);}

        public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
    public UserDTO createUser(UserDTO userDTO) {
        User user = fromUserDto(userDTO); // Convert UserDTO to User using fromUserDto function
        user = userRepository.save(user);
        return toUserDto(user); // Convert saved User to UserDTO
    }

    public List<User> searchUsers(Map<String, String> searchParams) {
        List<User> results = new ArrayList<>();
        for (Map.Entry<String, String> entry : searchParams.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            // Adapt field names and logic based on your User entity fields
            switch (field) {
                case "fullname":
                    results.addAll(userRepository.findByFullnameRegex(value));
                    break;
                case "email":
                    results.addAll(userRepository.findByEmail(value));
                    break;
                case "diplome":
                    results.addAll(userRepository.findByDiplome(value));
                        break;
                // Add more conditions for other fields
            }
        }

        return results.stream().distinct().collect(Collectors.toList());
    }

    /* -------------- DTO ------------*/

    public UserDTO toUserDto(User user) {
        return new UserDTO(user);
    }

    private User fromUserDto(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFullname(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setDatenaissance(userDTO.getDateNaissance());
        user.setDiplome(userDTO.getDiplome());

        // Handle formateur information if present in DTO
        if (userDTO.getSpecialite() != null) {
            FormateurDetails formateurDetails = new FormateurDetails();
            formateurDetails.setSpecialite(userDTO.getSpecialite());
            formateurDetails.setUrlCv(userDTO.getUrlCv());
            formateurDetails.setDateDeb(userDTO.getDateDebFormateur());
            user.setFormateurDetails(formateurDetails);
        }

        // Consider logic for setting formations based on formationIds (not shown here)

        return user;
    }
}
