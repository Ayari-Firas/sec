package org.sid.Elearning.Controller;

import java.util.List;

import org.sid.Elearning.DTO.ProjetDTO;
import org.sid.Elearning.Services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @PostMapping("/create")
    public ResponseEntity<ProjetDTO> createProjet(@RequestBody ProjetDTO projetDTO) {
        ProjetDTO savedProjet = projetService.saveProjet(projetDTO);
        return new ResponseEntity<>(savedProjet, HttpStatus.CREATED); // Created status for successful creation
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetDTO> updateProjet(@PathVariable String id, @RequestBody ProjetDTO projetDTO) {
        ProjetDTO updatedProjet = projetService.updateProjet(id, projetDTO);
        return new ResponseEntity<>(updatedProjet, HttpStatus.OK); // OK status for successful update
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable String id) {
        projetService.deleteProjet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No Content status on successful deletion
    }

    @GetMapping
    public ResponseEntity<List<ProjetDTO>> getAllProjets() {
        List<ProjetDTO> projets = projetService.getAllProjets();
        return new ResponseEntity<>(projets, HttpStatus.OK); // OK status for successful retrieval
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetById(@PathVariable String id) {
        ProjetDTO projet = projetService.getProjetById(id);
        return new ResponseEntity<>(projet, projet != null ? HttpStatus.OK : HttpStatus.NOT_FOUND); // OK for found, NOT_FOUND for not found
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjetDTO>> getProjetsByUserId(@PathVariable String userId) {
        List<ProjetDTO> projets = projetService.getProjetsByUserId(userId);
        return new ResponseEntity<>(projets, HttpStatus.OK); // OK status for successful retrieval
    }
}
