package org.sid.Elearning.entities;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.sid.Elearning.DTO.ResourceDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class Formation {
    @Id
    private String id;
    private String name;
    private String contenu;
    private String duree;
    private String type;
    private String description;
    private String Groupe;
    private Date DateDeb;
    @DBRef
    private List<AvisFormation> avisFormations=new ArrayList<>();
    @DBRef
    private List<String> Users=new ArrayList<>();
    @DBRef
    private String Formateur;
    private List<ResourceDTO> ress=new ArrayList<>();
    private Collection <String> videos=new ArrayList<>();
    private Boolean premium=false;
    public boolean isPremiumFormation() {
        return this.premium; // Assuming you have a 'premium' field (boolean)

    }


}
