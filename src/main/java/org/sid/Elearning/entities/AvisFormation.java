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
@Setter
@Getter
public class AvisFormation {
    @Id
    private String id;
    private String Contenu;
    @DBRef
    private String Formation;
    @DBRef
    private String User;

}
