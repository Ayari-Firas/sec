package org.sid.Elearning.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AvisFormateur {
@Id
    private String id;
private int note;
    @DBRef
    private Formation formationId ;
    @DBRef
    private User userId;



}
