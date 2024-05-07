package org.sid.Elearning.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
public class FormateurDetails {
    private String specialite;
    private String urlCv;
    private String diplome;
    private Date dateDeb;
}