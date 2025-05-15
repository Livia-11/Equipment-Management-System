package com.institution.KireziEquipmentManagementSystem.service.impl;

import com.institution.KireziEquipmentManagementSystem.dto.AllocationLogDTO;
import com.institution.KireziEquipmentManagementSystem.repository.AllocationLogRepository;
import com.institution.KireziEquipmentManagementSystem.service.AllocationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllocationLogServiceImpl implements AllocationLogService {
    private final AllocationLogRepository allocationLogRepository;

    @Override
    public List<AllocationLogDTO> getAll() {
        return allocationLogRepository.findAll().stream().map(AllocationLogDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AllocationLogDTO> getByEquipment(Long equipmentId) {
        return allocationLogRepository.findByEquipmentId(equipmentId).stream().map(AllocationLogDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AllocationLogDTO> getByUser(Long userId) {
        return allocationLogRepository.findByUserId(userId).stream().map(AllocationLogDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AllocationLogDTO> getByAction(String action) {
        return allocationLogRepository.findByAction(action).stream().map(AllocationLogDTO::new).collect(Collectors.toList());
    }
} 