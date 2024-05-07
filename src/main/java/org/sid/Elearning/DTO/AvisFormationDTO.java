package org.sid.Elearning.DTO;

import lombok.Data;
import org.sid.Elearning.entities.AvisFormation;

@Data
public class AvisFormationDTO {
    private String id;
    private String content;
    private String formationId; // Consider using a String for the ID
    private String userId; // Consider using a String for the ID
    public AvisFormationDTO(AvisFormation avisFormation) {
        this.content = avisFormation.getContenu();
        this.formationId = avisFormation.getFormation();
        if (avisFormation.getUser() != null) {
            this.userId = avisFormation.getUser();
        } else {
            // Handle the case where the user object is null (optional)
            this.userId = null; // Or set a default value
        }    }


}

