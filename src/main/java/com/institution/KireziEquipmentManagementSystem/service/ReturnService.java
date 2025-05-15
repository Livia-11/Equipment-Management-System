package com.institution.KireziEquipmentManagementSystem.service;

import com.institution.KireziEquipmentManagementSystem.dto.ReturnDTO;
import com.institution.KireziEquipmentManagementSystem.model.Return;
import java.util.List;

public interface ReturnService {
    ReturnDTO create(ReturnDTO dto);
    List<ReturnDTO> getAll();
    ReturnDTO getById(Long id);
    List<ReturnDTO> getByCondition(Return.Condition condition);
} 