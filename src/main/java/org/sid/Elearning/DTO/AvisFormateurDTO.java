package org.sid.Elearning.DTO;

import lombok.Data;

@Data
public class AvisFormateurDTO {
    private String id;
    private int note;
    private String formationId; // Consider using a String for the ID
    private String userId; // Consider using a String for the ID
}


