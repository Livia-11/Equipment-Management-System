package com.institution.KireziEquipmentManagementSystem.repository;

import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByType(String type);
    List<Equipment> findByStatus(Equipment.Status status);
    List<Equipment> findByLocation(String location);
    List<Equipment> findByNameContainingIgnoreCase(String name);
} 