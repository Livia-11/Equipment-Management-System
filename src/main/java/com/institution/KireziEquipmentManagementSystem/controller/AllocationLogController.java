package com.institution.KireziEquipmentManagementSystem.controller;

import com.institution.KireziEquipmentManagementSystem.dto.AllocationLogDTO;
import com.institution.KireziEquipmentManagementSystem.service.AllocationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-logs")
@RequiredArgsConstructor
public class AllocationLogController {
    private final AllocationLogService allocationLogService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AllocationLogDTO>> getAllLogs() {
        return ResponseEntity.ok(allocationLogService.getAll());
    }

    @GetMapping("/equipment/{equipmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AllocationLogDTO>> getLogsByEquipment(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(allocationLogService.getByEquipment(equipmentId));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<List<AllocationLogDTO>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(allocationLogService.getByUser(userId));
    }

    @GetMapping("/action/{action}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AllocationLogDTO>> getLogsByAction(@PathVariable String action) {
        return ResponseEntity.ok(allocationLogService.getByAction(action));
    }
} 