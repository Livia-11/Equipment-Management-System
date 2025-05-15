package com.institution.KireziEquipmentManagementSystem.dto;

import com.institution.KireziEquipmentManagementSystem.model.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long id;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private String userName;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    private String equipmentName;
    
    @NotBlank(message = "Purpose is required")
    @Size(max = 200, message = "Purpose cannot exceed 200 characters")
    private String purpose;
    
    private LocalDateTime requestDate;
    
    private Request.Status status;

    // Constructor to convert from entity to DTO
    public RequestDTO(Request request) {
        this.id = request.getId();
        this.userId = request.getUser().getId();
        this.userName = request.getUser().getFullName();
        this.equipmentId = request.getEquipment().getId();
        this.equipmentName = request.getEquipment().getName();
        this.purpose = request.getPurpose();
        this.requestDate = request.getRequestDate();
        this.status = request.getStatus();
    }
} 