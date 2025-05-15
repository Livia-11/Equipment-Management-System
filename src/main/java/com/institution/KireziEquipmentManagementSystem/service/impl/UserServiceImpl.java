 package com.institution.KireziEquipmentManagementSystem.service.impl;

import com.institution.KireziEquipmentManagementSystem.dto.UserDTO;
import com.institution.KireziEquipmentManagementSystem.model.User;
import com.institution.KireziEquipmentManagementSystem.repository.UserRepository;
import com.institution.KireziEquipmentManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO, String password) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userDTO.getRole());
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id).map(UserDTO::new)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UserDTO getByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDTO::new)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
