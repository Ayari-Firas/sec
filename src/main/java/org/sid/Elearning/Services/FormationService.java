package org.sid.Elearning.Services;

import org.sid.Elearning.DTO.FormationDTO;
import org.sid.Elearning.Repository.FormationRepo;
import org.sid.Elearning.entities.AvisFormation;
import org.sid.Elearning.entities.Formation;
import org.sid.Elearning.entities.Resource;
import org.sid.Elearning.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    private final FormationRepo formationRepository;
    /* -------------- Crud Formation ------------*/

    public FormationService(FormationRepo formationRepository) {
        this.formationRepository = formationRepository;
    }
    // Create
    public Formation createFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    // Read (All)
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    // Read (Paginated)
    public Page<Formation> getAllFormations(Pageable pageable) {
        return formationRepository.findAll(pageable);
    }

    // Read (Filtered) - Implement filtering logic based on your requirements (e.g., by name, type, date, etc.)
    public List<Formation> getFormationsByFilter(String filter) {
        // Implement filtering logic here (e.g., using repository methods or criteria)
        return formationRepository.findAll(); // Replace with filtered results
    }

    // Read (One by ID)
    public Optional<Formation> getFormationById(String id) {
        return formationRepository.findById(id);
    }

    // Update
    public Formation updateFormation(String id, Formation updatedFormation) {
        Optional<Formation> existingFormation = formationRepository.findById(id);
        if (existingFormation.isPresent()) {
            Formation formationToUpdate = existingFormation.get();
            // Update relevant fields (e.g., using a mapper or manually)
            formationToUpdate.setName(updatedFormation.getName());
            // ... (update other fields as needed)
            return formationRepository.save(formationToUpdate);
        } else {
            throw new RuntimeException("Formation not found with id: " + id);
        }
    }

    // Delete
    public void deleteFormation(String id) {
        formationRepository.deleteById(id); // Call the non-static method directly
    }
    /* -------------- DTO ------------*/
    public Formation dtoToFormation(FormationDTO formationDTO) {
        Formation formation = new Formation();
        formation.setId(formationDTO.getId());
        formation.setName(formationDTO.getName());
        formation.setContenu(formationDTO.getContenu());
        formation.setDuree(formationDTO.getDuree());
        formation.setType(formationDTO.getType());
        formation.setDescription(formationDTO.getDescription());
        formation.setGroupe(formationDTO.getGroupe());
        formation.setDateDeb(formationDTO.getDateDeb());
        // ... (set other fields as needed, handle collections)
        return formation;
    }

    // Formation to DTO
    public FormationDTO formationToDTO(Formation formation) {
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setId(formation.getId());
        formationDTO.setName(formation.getName());
        formationDTO.setContenu(formation.getContenu());
        formationDTO.setDuree(formationDTO.getDuree());
        formationDTO.setType(formation.getType());
        formationDTO.setDescription(formation.getDescription());
        formationDTO.setGroupe(formation.getGroupe());
        formationDTO.setDateDeb(formation.getDateDeb());
        // ... (set other fields as needed, handle collections)
        return formationDTO;
    }

}
