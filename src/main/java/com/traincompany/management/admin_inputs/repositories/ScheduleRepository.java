package com.traincompany.management.admin_inputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traincompany.management.admin_inputs.models.Schedule;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public List<Schedule> findAllByStatusId(Integer statusId);
}
