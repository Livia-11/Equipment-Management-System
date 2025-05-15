package com.institution.KireziEquipmentManagementSystem.controller;

import com.institution.KireziEquipmentManagementSystem.dto.ReturnDTO;
import com.institution.KireziEquipmentManagementSystem.model.Return;
import com.institution.KireziEquipmentManagementSystem.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class ReturnController {
    private final ReturnService returnService;

    @PostMapping
    public ResponseEntity<ReturnDTO> createReturn(@RequestBody ReturnDTO returnDTO) {
        return ResponseEntity.ok(returnService.create(returnDTO));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReturnDTO>> getAllReturns() {
        return ResponseEntity.ok(returnService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @returnService.getById(#id).getRequest().getUserId() == authentication.principal.id")
    public ResponseEntity<ReturnDTO> getReturnById(@PathVariable Long id) {
        return ResponseEntity.ok(returnService.getById(id));
    }

    @GetMapping("/condition/{condition}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReturnDTO>> getReturnsByCondition(@PathVariable Return.Condition condition) {
        return ResponseEntity.ok(returnService.getByCondition(condition));
    }
} 