package com.traincompany.management.admin_inputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traincompany.management.admin_inputs.models.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {
    
}
