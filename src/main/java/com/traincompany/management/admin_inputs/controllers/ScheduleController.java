package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.traincompany.management.admin_inputs.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.services.ScheduleService;
import com.traincompany.management.admin_inputs.services.StatusService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/schedules/")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final StatusService statusService;

    @GetMapping()
    public ResponseEntity<List<ScheduleDTO>> getSchedules() {
        try {
            return ResponseEntity.ok(scheduleService.findAll());
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
    public List<ScheduleDTO> schedules() throws Exception {
        return scheduleService.findAll();
    }

    @QueryMapping
    public ScheduleDTO scheduleById(@Argument Integer id) throws Exception {
        return scheduleService.findById(id);
    }

    @SchemaMapping(typeName = "Schedule")
    public StatusDTO status(ScheduleDTO schedule) throws Exception {
        return statusService.findById(schedule.statusId());
    }

    @MutationMapping
    public ScheduleDTO saveSchedule(@Argument ScheduleDTO schedule) throws Exception {
        return scheduleService.save(schedule);
    }

    @MutationMapping
    public Boolean deleteSchedule(@Argument Integer id) throws Exception {
        return scheduleService.deleteById(id);
    }
}