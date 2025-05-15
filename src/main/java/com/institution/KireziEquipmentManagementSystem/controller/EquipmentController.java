package com.institution.KireziEquipmentManagementSystem.controller;

import com.institution.KireziEquipmentManagementSystem.dto.EquipmentDTO;
import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import com.institution.KireziEquipmentManagementSystem.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        return ResponseEntity.ok(equipmentService.create(equipmentDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
        return ResponseEntity.ok(equipmentService.update(id, equipmentDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEquipment(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<EquipmentDTO>> searchEquipment(@RequestParam String name) {
        return ResponseEntity.ok(equipmentService.searchByName(name));
    }

    @GetMapping("/filter/status")
    public ResponseEntity<List<EquipmentDTO>> filterByStatus(@RequestParam Equipment.Status status) {
        return ResponseEntity.ok(equipmentService.filterByStatus(status));
    }

    @GetMapping("/filter/type")
    public ResponseEntity<List<EquipmentDTO>> filterByType(@RequestParam String type) {
        return ResponseEntity.ok(equipmentService.filterByType(type));
    }

    @GetMapping("/filter/location")
    public ResponseEntity<List<EquipmentDTO>> filterByLocation(@RequestParam String location) {
        return ResponseEntity.ok(equipmentService.filterByLocation(location));
    }
} 