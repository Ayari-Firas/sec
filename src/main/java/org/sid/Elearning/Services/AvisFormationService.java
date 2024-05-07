package org.sid.Elearning.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.sid.Elearning.DTO.AvisFormationDTO;
import org.sid.Elearning.Repository.AvisFormationRep;
import org.sid.Elearning.entities.AvisFormation;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class  AvisFormationService {

    @Autowired
    private AvisFormationRep avisFormationRepository;

    public AvisFormationDTO createAvisFormation(AvisFormationDTO avisFormationDTO) {
        AvisFormation avisFormation = new AvisFormation();
        avisFormation.setContenu(avisFormationDTO.getContent());
        avisFormation.setFormation(avisFormationDTO.getFormationId());
        avisFormation.setUser(avisFormationDTO.getUserId());
        avisFormation = avisFormationRepository.save(avisFormation);
        return new AvisFormationDTO(avisFormation);
    }

    public List<AvisFormationDTO> getAllAvisFormations() {
        List<AvisFormation> avisFormations = avisFormationRepository.findAll();
        return avisFormations.stream()
                .map(AvisFormationDTO::new)// Use avisFormation here
                .collect(Collectors.toList());
    }


    public AvisFormationDTO getAvisFormationById(String id) {
        AvisFormation avisFormation = avisFormationRepository.findById(id).orElseThrow();
        return new AvisFormationDTO(avisFormation);
    }

    public void deleteAvisFormation(String id) {
        avisFormationRepository.deleteById(id);
    }

    private AvisFormation toEntity(AvisFormationDTO avisFormationDTO) {
        AvisFormation avisFormation = new AvisFormation();
        avisFormation.setContenu(avisFormationDTO.getContent());
        avisFormation.setFormation(avisFormationDTO.getFormationId());
        avisFormation.setUser(avisFormationDTO.getUserId());
        return avisFormation;
    }
}
