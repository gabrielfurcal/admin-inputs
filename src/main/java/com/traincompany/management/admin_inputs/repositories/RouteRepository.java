package com.traincompany.management.admin_inputs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traincompany.management.admin_inputs.models.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    
}
