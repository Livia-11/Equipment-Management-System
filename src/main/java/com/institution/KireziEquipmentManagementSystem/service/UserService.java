package com.institution.KireziEquipmentManagementSystem.service;

import com.institution.KireziEquipmentManagementSystem.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
}