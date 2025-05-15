package com.institution.KireziEquipmentManagementSystem.service.impl;

import com.institution.KireziEquipmentManagementSystem.dto.RequestDTO;
import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import com.institution.KireziEquipmentManagementSystem.model.Request;
import com.institution.KireziEquipmentManagementSystem.model.User;
import com.institution.KireziEquipmentManagementSystem.repository.EquipmentRepository;
import com.institution.KireziEquipmentManagementSystem.repository.RequestRepository;
import com.institution.KireziEquipmentManagementSystem.repository.UserRepository;
import com.institution.KireziEquipmentManagementSystem.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    @Transactional
    public RequestDTO create(RequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Equipment equipment = equipmentRepository.findById(dto.getEquipmentId())
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));
        if (equipment.getQuantity() <= 0 || equipment.getStatus() != Equipment.Status.AVAILABLE) {
            throw new IllegalArgumentException("Equipment not available");
        }
        Request request = new Request();
        request.setUser(user);
        request.setEquipment(equipment);
        request.setPurpose(dto.getPurpose());
        request.setStatus(Request.Status.PENDING);
        return new RequestDTO(requestRepository.save(request));
    }

    @Override
    @Transactional
    public RequestDTO approve(Long id) {
        Request request = requestRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        if (request.getStatus() != Request.Status.PENDING) {
            throw new IllegalArgumentException("Request is not pending");
        }
        Equipment equipment = request.getEquipment();
        if (equipment.getQuantity() <= 0) {
            throw new IllegalArgumentException("No equipment available");
        }
        request.setStatus(Request.Status.APPROVED);
        equipment.setQuantity(equipment.getQuantity() - 1);
        if (equipment.getQuantity() == 0) {
            equipment.setStatus(Equipment.Status.IN_USE);
        }
        equipmentRepository.save(equipment);
        return new RequestDTO(requestRepository.save(request));
    }

    @Override
    @Transactional
    public RequestDTO reject(Long id) {
        Request request = requestRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        if (request.getStatus() != Request.Status.PENDING) {
            throw new IllegalArgumentException("Request is not pending");
        }
        request.setStatus(Request.Status.REJECTED);
        return new RequestDTO(requestRepository.save(request));
    }

    @Override
    public List<RequestDTO> getAll() {
        return requestRepository.findAll().stream().map(RequestDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> getByUser(Long userId) {
        return requestRepository.findByUserId(userId).stream().map(RequestDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> getByStatus(Request.Status status) {
        return requestRepository.findByStatus(status).stream().map(RequestDTO::new).collect(Collectors.toList());
    }

    @Override
    public RequestDTO getById(Long id) {
        return requestRepository.findById(id).map(RequestDTO::new)
            .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }
} 