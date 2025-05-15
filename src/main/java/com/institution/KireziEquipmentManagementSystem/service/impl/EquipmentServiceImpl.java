package com.institution.KireziEquipmentManagementSystem.service.impl;

import com.institution.KireziEquipmentManagementSystem.dto.EquipmentDTO;
import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import com.institution.KireziEquipmentManagementSystem.repository.EquipmentRepository;
import com.institution.KireziEquipmentManagementSystem.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Override
    public EquipmentDTO create(EquipmentDTO dto) {
        Equipment equipment = new Equipment();
        equipment.setName(dto.getName());
        equipment.setType(dto.getType());
        equipment.setQuantity(dto.getQuantity());
        equipment.setStatus(dto.getStatus());
        equipment.setLocation(dto.getLocation());
        return new EquipmentDTO(equipmentRepository.save(equipment));
    }

    @Override
    public EquipmentDTO update(Long id, EquipmentDTO dto) {
        Equipment equipment = equipmentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));
        equipment.setName(dto.getName());
        equipment.setType(dto.getType());
        equipment.setQuantity(dto.getQuantity());
        equipment.setStatus(dto.getStatus());
        equipment.setLocation(dto.getLocation());
        return new EquipmentDTO(equipmentRepository.save(equipment));
    }

    @Override
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public EquipmentDTO getById(Long id) {
        return equipmentRepository.findById(id).map(EquipmentDTO::new)
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));
    }

    @Override
    public List<EquipmentDTO> getAll() {
        return equipmentRepository.findAll().stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> searchByName(String name) {
        return equipmentRepository.findByNameContainingIgnoreCase(name).stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> filterByStatus(Equipment.Status status) {
        return equipmentRepository.findByStatus(status).stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> filterByType(String type) {
        return equipmentRepository.findByType(type).stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> filterByLocation(String location) {
        return equipmentRepository.findByLocation(location).stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }
} 