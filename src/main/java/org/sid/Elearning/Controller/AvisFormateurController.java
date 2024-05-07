package org.sid.Elearning.Controller;

import org.sid.Elearning.DTO.AvisFormateurDTO;
import org.sid.Elearning.Services.AvisFormateurService;
import org.sid.Elearning.entities.AvisFormateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/avis-formateurs")
public class AvisFormateurController {

    @Autowired
    private AvisFormateurService avisFormateurService;

    @PostMapping("/create")
    public ResponseEntity<AvisFormateurDTO> createAvisFormateur(@RequestBody AvisFormateurDTO avisFormateurDTO) {
        AvisFormateurDTO savedAvisFormateur = avisFormateurService.saveAvisFormateur(avisFormateurDTO);
        return new ResponseEntity<>(savedAvisFormateur, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvisFormateurDTO>> getAllAvisFormateurs() {
        List<AvisFormateurDTO> avisFormateurs = avisFormateurService.getAllAvisFormateurs();
        return new ResponseEntity<>(avisFormateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisFormateurDTO> getAvisFormateurById(@PathVariable String id) {
        AvisFormateurDTO avisFormateur = avisFormateurService.getAvisFormateurById(id);
        return new ResponseEntity<>(avisFormateur, avisFormateur != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvisFormateur(@PathVariable String id) {
        avisFormateurService.deleteAvisFormateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Additional API endpoints as needed (refer to comments in AvisFormateurService)
    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<AvisFormateurDTO>> getAvisFormateurByFormationId(@PathVariable String formationId) {
     List<AvisFormateurDTO> avisFormateurs = avisFormateurService.getAvisFormateurByFormationId(formationId);

        return new ResponseEntity<>(avisFormateurs, HttpStatus.OK);


    }
    private AvisFormateurDTO toDTO(AvisFormateur avisFormateur) {
        AvisFormateurDTO avisFormateurDTO = new AvisFormateurDTO();
        avisFormateurDTO.setId(avisFormateur.getId());
        avisFormateurDTO.setNote(avisFormateur.getNote());
        avisFormateurDTO.setFormationId(avisFormateur.getFormationId().getId());  // Assuming formation is stored as a reference ID
        avisFormateurDTO.setUserId(avisFormateur.getUserId().getId());        // Assuming user is stored as a reference ID
        return avisFormateurDTO;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AvisFormateurDTO>> getAvisFormateurByUserId(@PathVariable String userId) {
        List<AvisFormateurDTO> avisFormateurs = avisFormateurService.getAvisFormateurByUserId(userId);
        return new ResponseEntity<>(avisFormateurs, HttpStatus.OK);
    }
}
