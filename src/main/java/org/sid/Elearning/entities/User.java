package org.sid.Elearning.entities;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.sid.Elearning.Enum.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class User {
    @Id
    private String id ;
    private String fullname;
    private String email;
    private String Password;
    private int phone;
    private Date datenaissance;
    @Nullable
    private FormateurDetails formateurDetails;
    @DBRef // Reference to Formation collection
    private List<Formation> formations = new ArrayList<>();
    private String Diplome;
    private List<Role> roles = new ArrayList<>();
    public boolean isFormateur() {
        return this.formateurDetails != null ;

    }
    public boolean hasRole(Role role) {
        // Check if any of the user's roles match the provided role
        return this.getRoles().stream().anyMatch(userRole -> userRole.equals(role));
    }
    public void addRole(Role role) {
        // Add the role to the user's roles list
        this.getRoles().add(role);
    }
    { // Initialization block to add ROLE_ADMIN during object creation
        this.roles.add(Role.ROLE_USER);
    }
    private boolean persistRoleChanges = true; // Default to persist



private boolean defaultRolePersistenceEnabled;

private boolean needsRolePersistence() {
    return this.isPersistRoleChanges() // User setting overrides default
            ?defaultRolePersistenceEnabled:true;
}

}
