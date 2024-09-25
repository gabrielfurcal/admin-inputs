package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.traincompany.management.admin_inputs.DTOs.RouteDTO;
import com.traincompany.management.admin_inputs.DTOs.StationDTO;
import com.traincompany.management.admin_inputs.services.RouteService;
import com.traincompany.management.admin_inputs.services.StationService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/routes/")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final StationService stationService;

    @GetMapping()
    public ResponseEntity<List<RouteDTO>> getRoutes() {
        try {
            return ResponseEntity.ok(routeService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    * 
    * GraphQL Endpoints
    * 
    */
    @QueryMapping
    public List<RouteDTO> routes() throws Exception {
        return routeService.findAll();
    }

    @QueryMapping
    public RouteDTO routeById(@Argument Integer id) throws Exception {
        return routeService.findById(id);
    }

    @SchemaMapping(typeName = "Route")
    public StationDTO startStation(RouteDTO route) throws Exception {
        return stationService.findById(route.startStationId());
    }

    @SchemaMapping(typeName = "Route")
    public StationDTO endStation(RouteDTO route) throws Exception {
        return stationService.findById(route.endStationId());
    }

    @MutationMapping
    public RouteDTO saveRoute(@Argument RouteDTO route) throws Exception {
        return routeService.save(route);
    }

    @MutationMapping
    public Boolean deleteRoute(@Argument Integer id) throws Exception {
        return routeService.deleteById(id);
    }
}