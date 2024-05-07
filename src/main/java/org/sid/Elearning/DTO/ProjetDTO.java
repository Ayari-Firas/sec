package org.sid.Elearning.DTO;


import lombok.Data;

@Data
public class ProjetDTO {
    private String id;
    private String name;
    private String description;
    private String formationId; // Consider using a String for the ID
    private String userId; // Consider using a String for the ID
}
