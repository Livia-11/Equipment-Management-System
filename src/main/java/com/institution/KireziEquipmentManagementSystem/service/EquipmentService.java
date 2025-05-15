package com.institution.KireziEquipmentManagementSystem.service;

import com.institution.KireziEquipmentManagementSystem.dto.EquipmentDTO;
import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import java.util.List;

public interface EquipmentService {
    EquipmentDTO create(EquipmentDTO dto);
    EquipmentDTO update(Long id, EquipmentDTO dto);
    void delete(Long id);
    EquipmentDTO getById(Long id);
    List<EquipmentDTO> getAll();
    List<EquipmentDTO> searchByName(String name);
    List<EquipmentDTO> filterByStatus(Equipment.Status status);
    List<EquipmentDTO> filterByType(String type);
    List<EquipmentDTO> filterByLocation(String location);
} 