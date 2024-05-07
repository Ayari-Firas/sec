package org.sid.Elearning.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.sid.Elearning.DTO.ProjetDTO;
import org.sid.Elearning.entities.Projet;
import org.sid.Elearning.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;
    /* -------------- Crud Project ------------*/

    public ProjetDTO saveProjet(ProjetDTO projetDTO) {
        Projet projet = fromProjetDTO(projetDTO);
        projet = projetRepository.save(projet);
        return toProjetDTO(projet);
    }

    public ProjetDTO updateProjet(String id, ProjetDTO projetDTO) {
        Projet existingProjet = projetRepository.findById(id).orElseThrow();
        existingProjet.setName(projetDTO.getName());
        existingProjet.setDescription(projetDTO.getDescription());
        existingProjet.setFormation(projetDTO.getId());
        existingProjet.setUserId(projetDTO.getUserId());
        Projet Projet = projetRepository.save(existingProjet);
        return toProjetDTO(Projet);
    }
    public void deleteProjet(String id) {
        projetRepository.deleteById(id);
    }

    public List<ProjetDTO> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        return projets.stream().map(this::toProjetDTO).toList(); // Convert list of Projet to list of ProjetDTO
    }

    public ProjetDTO getProjetById(String id) {
        Projet projet = projetRepository.findById(id).orElseThrow();
        return toProjetDTO(projet);
    }
    public List<ProjetDTO> getProjetsByUserId(String userId) {

        List<Projet> projets = projetRepository.findByUserId(userId);
        return projets.stream().map(this::toProjetDTO).collect(Collectors.toList());
    }
    /* -------------- toDTO ------------*/

    private ProjetDTO toProjetDTO(Projet projet) {
        ProjetDTO projetDTO = new ProjetDTO();
        projetDTO.setId(projet.getId().toString()); // Assuming Long ID, convert to String
        projetDTO.setName(projet.getName());
        projetDTO.setDescription(projet.getDescription());
        projetDTO.setFormationId(projet.getFormation().toString()); // Assuming Long ID, convert to String
        projetDTO.setUserId(projet.getUserId().toString()); // Assuming Long ID, convert to String
        return projetDTO;
    }

    private Projet fromProjetDTO(ProjetDTO projetDTO) {
        Projet projet = new Projet();
        projet.setName(projetDTO.getName());
        projet.setDescription(projetDTO.getDescription());
        projet.setFormation((projetDTO.getId())); // Assuming String ID, convert to Long
        projet.setUserId((projetDTO.getUserId())); // Assuming String ID, convert to Long
        return projet;
    }
}
