package com.institution.KireziEquipmentManagementSystem.repository;

import com.institution.KireziEquipmentManagementSystem.model.AllocationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AllocationLogRepository extends JpaRepository<AllocationLog, Long> {
    List<AllocationLog> findByEquipmentId(Long equipmentId);
    List<AllocationLog> findByUserId(Long userId);
    List<AllocationLog> findByAction(String action);
    List<AllocationLog> findByAllocatedAtBetween(LocalDateTime start, LocalDateTime end);
} 