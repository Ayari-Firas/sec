/*package org.sid.Elearning.Services;

import org.sid.Elearning.DTO.AvisFormateurDTO;
import org.sid.Elearning.Repository.AvisFormateurRepository;
import org.sid.Elearning.Repository.FormationRepo;
import org.sid.Elearning.Repository.UserRepo;
import org.sid.Elearning.entities.AvisFormateur;
import org.sid.Elearning.entities.Formation;
import org.sid.Elearning.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;





@Service
public class AvisFormateurService {

    @Autowired
    private AvisFormateurRepository avisFormateurRepository;

    @Autowired
    private FormationRepo formationRepository;

    @Autowired
    private UserRepo userRepository;

    public AvisFormateurDTO saveAvisFormateur(AvisFormateurDTO avisFormateurDTO) {
        AvisFormateur avisFormateur = new AvisFormateur();
        avisFormateur.setNote(avisFormateurDTO.getNote());

        // Find Formation by ID (assuming formationId refers to the ID)
        Optional<Formation> formationOptional = formationRepository.findById(avisFormateurDTO.getFormationId());
        if (formationOptional.isPresent()) {
            avisFormateur.setFormation(formationOptional.get().getId());
        } else {
            // Handle case where formation is not found (throw exception or return null)
            throw new RuntimeException("Formation not found with ID: " + avisFormateurDTO.getFormationId());
        }

        // Find User by ID (assuming userId refers to the ID)
        Optional<User> userOptional = userRepository.findById(avisFormateurDTO.getUserId());
        if (userOptional.isPresent()) {
            avisFormateur.setUser(userOptional.get());
        } else {
            // Handle case where user is not found (throw exception or return null)
            throw new RuntimeException("User not found with ID: " + avisFormateurDTO.getUserId());
        }

        AvisFormateur savedAvisFormateur = avisFormateurRepository.save(avisFormateur);
        return toDTO(savedAvisFormateur);
    }

    public List<AvisFormateurDTO> getAllAvisFormateurs() {
        List<AvisFormateur> avisFormateurs = avisFormateurRepository.findAll();
        return avisFormateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AvisFormateurDTO getAvisFormateurById(String id) {
        Optional<AvisFormateur> avisFormateurOptional = avisFormateurRepository.findById(id);
        return avisFormateurOptional.map(this::toDTO).orElse(null);
    }

    public void deleteAvisFormateur(String id) {
        avisFormateurRepository.deleteById(id);
    }

    // Additional methods

    public List<AvisFormateurDTO> getAvisFormateurByFormationId(String formationId) {
        List<AvisFormateur> avisFormateurs = avisFormateurRepository.findByFormationId(formationId);
        return avisFormateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AvisFormateurDTO> getAvisFormateurByUserId(String userId) {
        List<AvisFormateur> avisFormateurs = avisFormateurRepository.findByUserId(userId);
        return avisFormateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private AvisFormateurDTO toDTO(AvisFormateur avisFormateur) {
        AvisFormateurDTO avisFormateurDTO = new AvisFormateurDTO();
        avisFormateurDTO.setId(avisFormateur.getId());
        avisFormateurDTO.setNote(avisFormateur.getNote());
        avisFormateurDTO.setFormationId(avisFormateur.getFormation() != null ? avisFormateur.getFormation().getId(): null);
        avisFormateurDTO.setUserId(avisFormateur.getUser() != null ? avisFormateur.getUser().getId() : null);
        return avisFormateurDTO;
    }

}*/
package org.sid.Elearning.Services;

import org.sid.Elearning.DTO.AvisFormateurDTO;
import org.sid.Elearning.Repository.AvisFormateurRepository;
import org.sid.Elearning.entities.AvisFormateur;
import org.sid.Elearning.entities.Formation;
import org.sid.Elearning.entities.User;
import org.sid.Elearning.Repository.FormationRepo;
import org.sid.Elearning.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvisFormateurService {

    @Autowired
    private AvisFormateurRepository avisFormateurRepository;

    @Autowired
    private FormationRepo formationRepository;

    @Autowired
    private UserRepo userRepository;

    public AvisFormateurDTO saveAvisFormateur(AvisFormateurDTO avisFormateurDTO) {
        AvisFormateur avisFormateur = new AvisFormateur();
        avisFormateur.setNote(avisFormateurDTO.getNote());

        // Find Formation by ID (assuming formationId refers to the ID)
        Optional<Formation> formationOptional = formationRepository.findById(avisFormateurDTO.getFormationId());
        if (formationOptional.isPresent()) {
            avisFormateur.setFormationId(formationOptional.get());
        } else {
            // Handle case where formation is not found (throw exception or return null)
            throw new RuntimeException("Formation not found with ID: " + avisFormateurDTO.getFormationId());
        }

        // Find User by ID (assuming userId refers to the ID)
        Optional<User> userOptional = userRepository.findById(avisFormateurDTO.getUserId());
        if (userOptional.isPresent()) {
            avisFormateur.setUserId(userOptional.get());
        } else {
            // Handle case where user is not found (throw exception or return null)
            throw new RuntimeException("User not found with ID: " + avisFormateurDTO.getUserId());
        }

        AvisFormateur savedAvisFormateur = avisFormateurRepository.save(avisFormateur);
        return toDTO(savedAvisFormateur);
    }

    public List<AvisFormateurDTO> getAllAvisFormateurs() {
        List<AvisFormateur> avisFormateurs = avisFormateurRepository.findAll();
        return avisFormateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AvisFormateurDTO getAvisFormateurById(String id) {
        Optional<AvisFormateur> avisFormateurOptional = avisFormateurRepository.findById(id);
        return avisFormateurOptional.map(this::toDTO).orElse(null);
    }

    public void deleteAvisFormateur(String id) {
        avisFormateurRepository.deleteById(id);
    }

    // Additional methods

    // ... existing code

    public List<AvisFormateurDTO> getAvisFormateurByFormationId(String formationId) {
        List<AvisFormateur> avisFormateurs = avisFormateurRepository.findByFormationId(formationId);
        return avisFormateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AvisFormateurDTO> getAvisFormateurByUserId(String userId) {
        List<AvisFormateur> avisFormateurs = avisFormateurRepository.findByUserId(userId);
        return avisFormateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }

// ... existing code



    private AvisFormateurDTO toDTO(AvisFormateur avisFormateur) {
        AvisFormateurDTO avisFormateurDTO = new AvisFormateurDTO();
        avisFormateurDTO.setId(avisFormateur.getId());
        avisFormateurDTO.setNote(avisFormateur.getNote());
        avisFormateurDTO.setFormationId(avisFormateur.getFormationId() != null ? avisFormateur.getFormationId().getId() : null);
        avisFormateurDTO.setUserId(avisFormateur.getUserId() != null ? avisFormateur.getUserId().getId() : null);
        return avisFormateurDTO;
    }
}
