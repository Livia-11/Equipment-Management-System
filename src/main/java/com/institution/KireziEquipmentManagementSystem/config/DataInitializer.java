package com.institution.KireziEquipmentManagementSystem.config;

import com.institution.KireziEquipmentManagementSystem.model.Equipment;
import com.institution.KireziEquipmentManagementSystem.model.User;
import com.institution.KireziEquipmentManagementSystem.repository.EquipmentRepository;
import com.institution.KireziEquipmentManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create admin user if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");
            admin.setRole(User.Role.ADMIN);
            admin.setFullName("System Administrator");
            userRepository.save(admin);
        }

        // Create sample equipment if none exists
        if (equipmentRepository.count() == 0) {
            // Sample Laptop
            Equipment laptop = new Equipment();
            laptop.setName("Dell Latitude");
            laptop.setType("Laptop");
            laptop.setQuantity(5);
            laptop.setStatus(Equipment.Status.AVAILABLE);
            laptop.setLocation("IT Department");
            equipmentRepository.save(laptop);

            // Sample Projector
            Equipment projector = new Equipment();
            projector.setName("Epson Projector");
            projector.setType("Projector");
            projector.setQuantity(2);
            projector.setStatus(Equipment.Status.AVAILABLE);
            projector.setLocation("Conference Room");
            equipmentRepository.save(projector);

            // Sample Camera
            Equipment camera = new Equipment();
            camera.setName("Canon EOS");
            camera.setType("Camera");
            camera.setQuantity(3);
            camera.setStatus(Equipment.Status.AVAILABLE);
            camera.setLocation("Media Room");
            equipmentRepository.save(camera);
        }
    }
} 