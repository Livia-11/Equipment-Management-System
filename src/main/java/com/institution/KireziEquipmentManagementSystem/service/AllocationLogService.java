package com.institution.KireziEquipmentManagementSystem.service;

import com.institution.KireziEquipmentManagementSystem.dto.AllocationLogDTO;
import java.util.List;

public interface AllocationLogService {
    List<AllocationLogDTO> getAll();
    List<AllocationLogDTO> getByEquipment(Long equipmentId);
    List<AllocationLogDTO> getByUser(Long userId);
    List<AllocationLogDTO> getByAction(String action);
} 