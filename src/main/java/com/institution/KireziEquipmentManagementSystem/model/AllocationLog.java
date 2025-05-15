package com.institution.KireziEquipmentManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "allocation_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllocationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime allocatedAt;

    @Column(nullable = false)
    private String action;

    @PrePersist
    protected void onCreate() {
        if (allocatedAt == null) {
            allocatedAt = LocalDateTime.now();
        }
        if (action == null) {
            action = "ALLOCATED";
        }
    }
} 