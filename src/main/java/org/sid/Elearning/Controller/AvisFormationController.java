package org.sid.Elearning.Controller;

import java.util.List;

import org.sid.Elearning.DTO.AvisFormationDTO;
import org.sid.Elearning.Services.AvisFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This controller handles REST API requests
@RequestMapping("/api/avisFormation") // Base path for AvisFormation endpoints
public class AvisFormationController {

    @Autowired
    private AvisFormationService avisFormationService;

    @PostMapping // HTTP POST request for creating AvisFormation
    public ResponseEntity<AvisFormationDTO> createAvisFormation(@RequestBody AvisFormationDTO avisFormationDTO) {
        AvisFormationDTO savedAvisFormation = avisFormationService.createAvisFormation(avisFormationDTO);
        return new ResponseEntity<>(savedAvisFormation, HttpStatus.CREATED);
    }

    @GetMapping // HTTP GET request for retrieving all AvisFormations
    public ResponseEntity<List<AvisFormationDTO>> getAllAvisFormations() {
        List<AvisFormationDTO> avisFormations = avisFormationService.getAllAvisFormations();
        return new ResponseEntity<>(avisFormations, HttpStatus.OK);
    }

    @GetMapping("/{id}") // HTTP GET request for retrieving AvisFormation by ID
    public ResponseEntity<AvisFormationDTO> getAvisFormationById(@PathVariable String id) {
        AvisFormationDTO avisFormation = avisFormationService.getAvisFormationById(id);
        return new ResponseEntity<>(avisFormation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // HTTP DELETE request for deleting AvisFormation
    public ResponseEntity<Void> deleteAvisFormation(@PathVariable String id) {
        avisFormationService.deleteAvisFormation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Success with no content returned
    }
}
