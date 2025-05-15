 package com.institution.KireziEquipmentManagementSystem.service;

import com.institution.KireziEquipmentManagementSystem.dto.UserDTO;
import com.institution.KireziEquipmentManagementSystem.model.User;
import java.util.List;

public interface UserService {
    UserDTO register(UserDTO userDTO, String password);
    UserDTO getById(Long id);
    UserDTO getByEmail(String email);
    List<UserDTO> getAll();
    void delete(Long id);
}