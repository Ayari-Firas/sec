package org.sid.Elearning.DTO;


import lombok.Data;
import org.sid.Elearning.entities.Formation;
import org.sid.Elearning.entities.User;

import java.util.Date;
import java.util.List;

@Data
public class FormationDTO {
    private String id;
    private String name;
    private String contenu;
    private String duree;
    private String type;
    private String description;
    private String groupe;
    private Date dateDeb;
    private List<ResourceDTO> resources; // List of ResourceDTO objects
    private List<String> userIds;
    private String formateurId;
    private List<String> videos;
}
