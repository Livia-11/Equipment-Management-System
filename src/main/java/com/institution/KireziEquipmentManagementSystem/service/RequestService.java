package com.institution.KireziEquipmentManagementSystem.service;

import com.institution.KireziEquipmentManagementSystem.dto.RequestDTO;
import com.institution.KireziEquipmentManagementSystem.model.Request;
import java.util.List;

public interface RequestService {
    RequestDTO create(RequestDTO dto);
    RequestDTO approve(Long id);
    RequestDTO reject(Long id);
    List<RequestDTO> getAll();
    List<RequestDTO> getByUser(Long userId);
    List<RequestDTO> getByStatus(Request.Status status);
    RequestDTO getById(Long id);
} 