package org.sid.Elearning.Services;

import org.sid.Elearning.DTO.AdminDTO;
import org.sid.Elearning.entities.admin;
import org.sid.Elearning.Repository.adminrep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private adminrep adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<admin> getAdminById(String id) {
        return adminRepository.findById(id);
    }

    public admin createAdmin(AdminDTO adminDTO) {
        admin admin = new admin();
        admin.setFullname(adminDTO.getFullName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword())); // Encode password
        return adminRepository.save(admin);
    }

    public admin updateAdmin(String id, AdminDTO adminDTO) {
        Optional<admin> existingAdmin = adminRepository.findById(id);
        if (existingAdmin.isPresent()) {
            admin admin = existingAdmin.get();
            admin.setFullname(adminDTO.getFullName());
            admin.setEmail(adminDTO.getEmail());
            return adminRepository.save(admin);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }

    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }
}
