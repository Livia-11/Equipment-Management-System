package com.institution.KireziEquipmentManagementSystem.repository;

import com.institution.KireziEquipmentManagementSystem.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {
    Optional<Return> findByRequestId(Long requestId);
    List<Return> findByCondition(Return.Condition condition);
    List<Return> findByReturnDateBetween(LocalDateTime start, LocalDateTime end);
} 