package com.institution.KireziEquipmentManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "returns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition condition;

    private String notes;

    @PrePersist
    protected void onCreate() {
        returnDate = LocalDateTime.now();
    }

    public enum Condition {
        GOOD, DAMAGED
    }
} 