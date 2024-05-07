package org.sid.Elearning.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.Elearning.Enum.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
@Data @AllArgsConstructor @NoArgsConstructor

public class admin {
    @Id


    private String id;
    private  String fullname;
    private String email;
    private String password;

    private List<Role> roles = new ArrayList<>();
    { // Initialization block to add ROLE_ADMIN during object creation
        this.roles.add(Role.ROLE_ADMIN);
    }
    public boolean hasRole(Role role) {
        // Check if any of the user's roles match the provided role
        return this.getRoles().stream().anyMatch(userRole -> userRole.equals(role));
    }
    public void addRole(Role role) {
        // Add the role to the user's roles list
        this.getRoles().add(role);
    }

}
