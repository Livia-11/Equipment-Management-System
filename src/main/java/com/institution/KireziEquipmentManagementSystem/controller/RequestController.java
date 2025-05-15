package com.institution.KireziEquipmentManagementSystem.controller;

import com.institution.KireziEquipmentManagementSystem.dto.RequestDTO;
import com.institution.KireziEquipmentManagementSystem.model.Request;
import com.institution.KireziEquipmentManagementSystem.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(requestService.create(requestDTO));
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RequestDTO> approveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.approve(id));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RequestDTO> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.reject(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAll());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<List<RequestDTO>> getRequestsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getByUser(userId));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RequestDTO>> getRequestsByStatus(@PathVariable Request.Status status) {
        return ResponseEntity.ok(requestService.getByStatus(status));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @requestService.getById(#id).getUserId() == authentication.principal.id")
    public ResponseEntity<RequestDTO> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getById(id));
    }
} 