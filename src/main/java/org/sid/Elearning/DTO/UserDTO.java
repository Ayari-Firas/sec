package org.sid.Elearning.DTO;

import lombok.Data;
import org.sid.Elearning.entities.FormateurDetails;
import org.sid.Elearning.entities.User;

import java.util.Date;
import java.util.List;


@Data
public class UserDTO {
    private String id;
    private String FullName;
    private String email;
    private int phone;
    private Date dateNaissance;
    private String diplome;

    // Optional formateur fields (all nullable)
    private String specialite;
    private String urlCv;
    private Date dateDebFormateur;

    // Consider using a separate DTO for enrolled formations or a simplified list
    private List<String> formationIds; // IDs of enrolled formations

    public UserDTO(User user) {
        this.id = user.getId();
        this.FullName = user.getFullname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.dateNaissance = user.getDatenaissance();
        this.diplome = user.getDiplome();


        // Populate optional formateur fields if available
        FormateurDetails formateurDetails = user.getFormateurDetails();
        if (formateurDetails != null) {
            this.specialite = formateurDetails.getSpecialite();
            this.urlCv = formateurDetails.getUrlCv();
            this.dateDebFormateur = formateurDetails.getDateDeb();

        }
}}

