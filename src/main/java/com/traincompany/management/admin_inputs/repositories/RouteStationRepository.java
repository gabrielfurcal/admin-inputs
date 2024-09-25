package com.traincompany.management.admin_inputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traincompany.management.admin_inputs.models.RouteStation;

public interface RouteStationRepository extends JpaRepository<RouteStation, Integer> {
    
}
