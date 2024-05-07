package org.sid.Elearning.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.sid.Elearning.Services.UserService;

import org.sid.Elearning.DTO.UserDTO;
import org.sid.Elearning.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /* -------------- Existing CRUD Endpoints (unchanged) ------------ */

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // Assuming UserDTO has fields for creating a new user
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // Assuming UserDTO has fields for updating an existing user
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    // Assuming User has an ID field (e.g., String id)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    /* -------------- Additional Endpoints ------------ */

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return users.stream().map(userService::toUserDto).collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<UserDTO> searchUsers(@RequestParam Map<String, String> searchParams) {
        // Implement logic to search users based on provided parameters in searchParams (e.g., email, fullname)
        // You can call UserService methods or create custom logic here
        List<User> users = userService.searchUsers(searchParams);
        return users.stream().map(userService::toUserDto).collect(Collectors.toList());
    }

    // Consider adding more endpoints for filtering users based on specific criteria (e.g., role, formation)
}
