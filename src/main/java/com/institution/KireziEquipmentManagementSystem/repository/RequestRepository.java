package com.institution.KireziEquipmentManagementSystem.repository;

import com.institution.KireziEquipmentManagementSystem.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);
    List<Request> findByEquipmentId(Long equipmentId);
    List<Request> findByStatus(Request.Status status);
    List<Request> findByRequestDateBetween(LocalDateTime start, LocalDateTime end);
    List<Request> findByUserIdAndStatus(Long userId, Request.Status status);
} 