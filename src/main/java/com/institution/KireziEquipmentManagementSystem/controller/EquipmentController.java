package com.institution.KireziEquipmentManagementSystem.controller;

import com.institution.KireziEquipmentManagementSystem.dto.EquipmentDTO;
import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import com.institution.KireziEquipmentManagementSystem.service.EquipmentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> createEquipment(@RequestBody @Valid EquipmentDTO equipmentDTO) {
        try {
            return ResponseEntity.ok(equipmentService.create(equipmentDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while creating equipment");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id, @RequestBody @Valid EquipmentDTO equipmentDTO) {
        try {
            return ResponseEntity.ok(equipmentService.update(id, equipmentDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating equipment");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while deleting equipment");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(equipmentService.getById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while retrieving equipment");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEquipment() {
        try {
            return ResponseEntity.ok(equipmentService.getAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while retrieving equipment list");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEquipment(@RequestParam String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Search name cannot be empty");
            }
            return ResponseEntity.ok(equipmentService.searchByName(name));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while searching equipment");
        }
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