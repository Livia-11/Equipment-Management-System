package com.institution.KireziEquipmentManagementSystem.dto;

import com.institution.KireziEquipmentManagementSystem.model.AllocationLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllocationLogDTO {
    private Long id;
    private Long equipmentId;
    private String equipmentName;
    private Long userId;
    private String userName;
    private LocalDateTime allocatedAt;
    private String action;

    public AllocationLogDTO(AllocationLog log) {
        this.id = log.getId();
        this.equipmentId = log.getEquipment().getId();
        this.equipmentName = log.getEquipment().getName();
        this.userId = log.getUser().getId();
        this.userName = log.getUser().getFullName();
        this.allocatedAt = log.getAllocatedAt();
        this.action = log.getAction();
    }
} 