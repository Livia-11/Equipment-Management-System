package com.institution.KireziEquipmentManagementSystem.dto;

import com.institution.KireziEquipmentManagementSystem.model.Return;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDTO {
    private Long id;
    @NotNull(message = "Request ID is required")
    private Long requestId;
    private Long equipmentId;
    private String equipmentName;
    private Long userId;
    private String userName;
    private LocalDateTime returnDate;
    @NotNull(message = "Condition is required")
    private Return.Condition condition;
    @Size(max = 200, message = "Notes cannot exceed 200 characters")
    private String notes;

    public ReturnDTO(Return returnEntity) {
        this.id = returnEntity.getId();
        this.requestId = returnEntity.getRequest().getId();
        this.equipmentId = returnEntity.getRequest().getEquipment().getId();
        this.equipmentName = returnEntity.getRequest().getEquipment().getName();
        this.userId = returnEntity.getRequest().getUser().getId();
        this.userName = returnEntity.getRequest().getUser().getName();
        this.returnDate = returnEntity.getReturnDate();
        this.condition = returnEntity.getCondition();
        this.notes = returnEntity.getNotes();
    }
} 