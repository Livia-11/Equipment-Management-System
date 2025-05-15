package com.institution.KireziEquipmentManagementSystem.dto;

import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;
    
    @NotBlank(message = "Type is required")
    @Size(max = 50, message = "Type cannot exceed 50 characters")
    private String type;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;
    
    private Equipment.Status status;
    
    @Size(max = 200, message = "Location cannot exceed 200 characters")
    private String location;

    // Constructor to convert from entity to DTO
    public EquipmentDTO(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.type = equipment.getType();
        this.quantity = equipment.getQuantity();
        this.status = equipment.getStatus();
        this.location = equipment.getLocation();
    }
} 