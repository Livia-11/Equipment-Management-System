package com.institution.KireziEquipmentManagementSystem.service.impl;

import com.institution.KireziEquipmentManagementSystem.dto.ReturnDTO;
import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import com.institution.KireziEquipmentManagementSystem.model.Request;
import com.institution.KireziEquipmentManagementSystem.model.Return;
import com.institution.KireziEquipmentManagementSystem.repository.EquipmentRepository;
import com.institution.KireziEquipmentManagementSystem.repository.RequestRepository;
import com.institution.KireziEquipmentManagementSystem.repository.ReturnRepository;
import com.institution.KireziEquipmentManagementSystem.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService {
    private final ReturnRepository returnRepository;
    private final RequestRepository requestRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    @Transactional
    public ReturnDTO create(ReturnDTO dto) {
        Request request = requestRepository.findById(dto.getRequestId())
            .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        if (request.getStatus() != Request.Status.APPROVED) {
            throw new IllegalArgumentException("Request is not approved");
        }
        if (returnRepository.findByRequestId(request.getId()).isPresent()) {
            throw new IllegalArgumentException("Return already exists for this request");
        }
        Return ret = new Return();
        ret.setRequest(request);
        ret.setCondition(dto.getCondition());
        ret.setNotes(dto.getNotes());
        Return saved = returnRepository.save(ret);
        // Update equipment quantity and status
        Equipment equipment = request.getEquipment();
        equipment.setQuantity(equipment.getQuantity() + 1);
        equipment.setStatus(Equipment.Status.AVAILABLE);
        equipmentRepository.save(equipment);
        return new ReturnDTO(saved);
    }

    @Override
    public List<ReturnDTO> getAll() {
        return returnRepository.findAll().stream().map(ReturnDTO::new).collect(Collectors.toList());
    }

    @Override
    public ReturnDTO getById(Long id) {
        return returnRepository.findById(id).map(ReturnDTO::new)
            .orElseThrow(() -> new IllegalArgumentException("Return not found"));
    }

    @Override
    public List<ReturnDTO> getByCondition(Return.Condition condition) {
        return returnRepository.findByCondition(condition).stream().map(ReturnDTO::new).collect(Collectors.toList());
    }
} 