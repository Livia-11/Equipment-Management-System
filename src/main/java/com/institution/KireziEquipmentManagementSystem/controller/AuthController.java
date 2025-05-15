package com.institution.KireziEquipmentManagementSystem.controller;

import com.institution.KireziEquipmentManagementSystem.dto.UserDTO;
import com.institution.KireziEquipmentManagementSystem.security.JwtTokenProvider;
import com.institution.KireziEquipmentManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.get("username"),
                loginRequest.get("password")
            )
        );

        String token = jwtTokenProvider.generateToken(authentication);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.create(userDTO);
        return ResponseEntity.ok(createdUser);
    }
} 