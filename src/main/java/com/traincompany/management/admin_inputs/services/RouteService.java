package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.RouteDTO;
import com.traincompany.management.admin_inputs.models.Route;
import com.traincompany.management.admin_inputs.repositories.RouteRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {
    private final RouteRepository routeRepository;
    private final Mapper mapper;

    public List<RouteDTO> findAll() throws Exception {
        try {
            var dbRoutes = routeRepository.findAll();
            var routesList = dbRoutes.stream().map(route -> mapper.map(route)).toList();

            return routesList;
        } catch (Exception ex) {
            log.error("Error at getting routes: {}", ex.getMessage());
            throw new Exception("Error at getting routes");
        }
    }

    public RouteDTO findById(Integer id) throws Exception {
        try {
            Route dbRoute = routeRepository.findById(id).orElseThrow(() -> new Exception("Route not found"));
            var route = mapper.map(dbRoute);
            
            return route;
        } catch (Exception ex) {
            log.error("Error at getting route: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    public RouteDTO save(RouteDTO route) throws Exception {
        try {
            if(route.id() == null) {
                Route routeToSave = mapper.map(route);
                routeToSave = routeRepository.save(routeToSave);

                return mapper.map(routeToSave);
            } else {
                Route routeToUpdate = routeRepository.findById(route.id()).get();
                routeToUpdate.setStartStationId(route.startStationId());
                routeToUpdate.setEndStationId(route.endStationId());
                routeToUpdate.setDistance(route.distance());

                routeToUpdate = routeRepository.save(routeToUpdate);

                return mapper.map(routeToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving route: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Route routeToDelete = routeRepository.findById(id).get();

            routeRepository.delete(routeToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting route with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }    
}