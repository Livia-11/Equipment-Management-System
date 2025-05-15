DELIMITER //
DROP TRIGGER IF EXISTS after_request_approved //
CREATE TRIGGER after_request_approved
AFTER UPDATE ON request
FOR EACH ROW
BEGIN
    IF NEW.status = 'APPROVED' AND OLD.status != 'APPROVED' THEN
        INSERT INTO allocation_logs (equipment_id, user_id, allocatedAt, action)
        VALUES (NEW.equipment_id, NEW.user_id, NOW(), 'ALLOCATED');
    END IF;
END //
DELIMITER ; 