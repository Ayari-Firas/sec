package org.sid.Elearning.Controller;

import jakarta.validation.Valid;
import org.sid.Elearning.DTO.AdminDTO;
import org.sid.Elearning.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sid.Elearning.entities.admin;


import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<admin>> getAllAdmins() {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<admin> getAdminById(@PathVariable String id) {
        Optional<admin> admin = adminService.getAdminById(id);
        if (admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<admin> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        admin admin = adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<admin> updateAdmin(@PathVariable String id, @Valid @RequestBody AdminDTO adminDTO) {
        try {
            admin admin = adminService.updateAdmin(id, adminDTO);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
