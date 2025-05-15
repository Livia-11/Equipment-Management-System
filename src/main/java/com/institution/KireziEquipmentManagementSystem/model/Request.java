package com.institution.KireziEquipmentManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false)
    private String purpose;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private Return returnDetails;

    @PrePersist
    protected void onCreate() {
        requestDate = LocalDateTime.now();
    }

    public enum Status {
        PENDING, APPROVED, REJECTED
    }
} 