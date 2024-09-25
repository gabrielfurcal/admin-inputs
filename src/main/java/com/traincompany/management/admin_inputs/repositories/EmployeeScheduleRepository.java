package com.traincompany.management.admin_inputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traincompany.management.admin_inputs.models.EmployeeSchedule;

public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Integer> {
    
}
