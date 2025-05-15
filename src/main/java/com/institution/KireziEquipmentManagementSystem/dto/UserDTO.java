package com.institution.KireziEquipmentManagementSystem.dto;

import com.institution.KireziEquipmentManagementSystem.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private User.Role role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.role = user.getRole();
        // Don't set password in DTO for security
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setFullName(this.fullName);
        user.setEmail(this.email);
        user.setRole(this.role);
        if (this.password != null) {
            user.setPassword(this.password);
        }
        return user;
    }
} 