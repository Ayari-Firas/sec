package org.sid.Elearning.Controller;


import org.sid.Elearning.DTO.FormationDTO;
import org.sid.Elearning.DTO.ResourceDTO;
import org.sid.Elearning.Exceptions.NoFormationsFoundException;
import org.sid.Elearning.Services.FormationService;
import org.sid.Elearning.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    private FormationService formationService;

    // Create
    @PostMapping("/create")
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO formationDTO) {
        Formation formation = formationService.dtoToFormation(formationDTO);
        return new ResponseEntity<>(formationService.formationToDTO(formationService.createFormation(formation)), HttpStatus.CREATED);
    }

    // Read (All)
    @GetMapping("/list")
    public ResponseEntity<List<FormationDTO>> getAllFormations() {
        List<Formation> formations = formationService.getAllFormations();
        List<FormationDTO> formationDTOs = new ArrayList<>();
        for (Formation formation : formations) {
            formationDTOs.add(formationService.formationToDTO(formation));
        }
        return new ResponseEntity<>(formationDTOs, HttpStatus.OK);
    }

    // Read (Paginated)
    @GetMapping("/paginated")
    public ResponseEntity<Page<FormationDTO>> getAllFormations(Pageable pageable) {
        Page<Formation> formationsPage = formationService.getAllFormations(pageable);
        return new ResponseEntity<>(formationsPage.map(formationService::formationToDTO), HttpStatus.OK); // Convert each Formation to FormationDTO
    }
    private static final ThreadLocal<String> currentFilter = new ThreadLocal<>();

    // Read (Filtered) - Implement filtering logic in FormationService
    @GetMapping("/filter/{filter}")
    public ResponseEntity<List<FormationDTO>> getFormationsByFilter(@PathVariable String filter) {
        currentFilter.set(filter); // Store filter in ThreadLocal

        List<Formation> formations = formationService.getFormationsByFilter(filter);
        List<FormationDTO> formationDTOs = new ArrayList<>();
        for (Formation formation : formations) {
            formationDTOs.add(formationService.formationToDTO(formation));

        }
        return getListWithStatus(formationDTOs, HttpStatus.OK); // Delegate to helper method


    }
    private  ResponseEntity<List<FormationDTO>> getListWithStatus(List<FormationDTO> formationDTOs, HttpStatus status) {
        String filter = currentFilter.get(); // Retrieve filter from ThreadLocal
        if (formationDTOs.isEmpty()) {
            throw new NoFormationsFoundException("No formations found with filter: " + filter);
        }
        currentFilter.remove(); // Remove filter after use (optional)
        return new ResponseEntity<>(formationDTOs, HttpStatus.OK);
    }

    // Read (One by ID)
    // Read (One by ID)
    @GetMapping("/{id}")
    public ResponseEntity<FormationDTO> getFormationById(@PathVariable String id) {
        Optional<Formation> formation = formationService.getFormationById(id);
        return formation.map(formationService::formationToDTO)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)) // Map to ResponseEntity<FormationDTO>
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Supplier returning ResponseEntity<FormationDTO>
    }


    // Update
    @PutMapping("/{id}")
    public ResponseEntity<FormationDTO> updateFormation(@PathVariable String id, @RequestBody FormationDTO formationDTO) {
        Formation formation = formationService.dtoToFormation(formationDTO);
// Assuming ResourceDTO constructor takes title and description as arguments
        List<ResourceDTO> updatedResources = formationDTO.getResources();
        Collection<ResourceDTO> existingResources = formation.getRess(); // Get existing resources

        /*for (ResourceDTO resourceDTO : updatedResources) {
            if (resourceDTO.getTitle() == null) {
                // Add new resource
                ResourceDTO newResource = new ResourceDTO(
                        resourceDTO.getTitle(),
                        resourceDTO.getDescription(),
                        resourceDTO.getUrl()
                );
                existingResources.add(newResource);
            } else {
                // Update existing resource (logic you already have)
                ResourceDTO existingResource = existingResources.stream()
                        .filter(r -> r.getTitle().equals(resourceDTO.getTitle())) // assuming title is unique
                        .findFirst().orElse(null);
                if (existingResource != null) {
                    existingResource.setDescription(resourceDTO.getDescription());
                    existingResource.setUrl(resourceDTO.getUrl());
                    existingResource.setNiveau(resourceDTO.getNiveau());
                    existingResource.setType(resourceDTO.getType());
                    // Update other resource fields as needed
                }
            }*/
        for (ResourceDTO resourceDTO : updatedResources) {
            boolean isNewResource = true; // Assume new by default

            for (ResourceDTO existingResource : existingResources) {
                if (resourceDTO.getTitle().equals(existingResource.getTitle())) {
                    // Existing resource found, update it
                    existingResource.setDescription(resourceDTO.getDescription());
                    existingResource.setUrl(resourceDTO.getUrl());
                    existingResource.setNiveau(resourceDTO.getNiveau());
                    existingResource.setType(resourceDTO.getType());
                    // Update other resource fields as needed
                    isNewResource = false; // No longer a new resource
                    break; // Stop searching once a match is found
                }
            }

            if (isNewResource) {
                // Add new resource
                ResourceDTO newResource = new ResourceDTO(
                        resourceDTO.getTitle(),
                        resourceDTO.getDescription(),
                        resourceDTO.getUrl()
                );
                existingResources.add(newResource);
            }



    }return new ResponseEntity<>( formationService.formationToDTO(formationService.
                updateFormation(id, formation)),HttpStatus.OK);}

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable String id) {
        formationService.deleteFormation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
