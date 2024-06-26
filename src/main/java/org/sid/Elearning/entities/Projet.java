package org.sid.Elearning.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Projet {
    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    private String Formation;
    @DBRef
    private String userId;


}
