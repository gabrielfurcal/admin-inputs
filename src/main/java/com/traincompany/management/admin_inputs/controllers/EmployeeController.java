package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.traincompany.management.admin_inputs.DTOs.EmployeeDTO;
import com.traincompany.management.admin_inputs.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
// import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/employees/")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        try {
            return ResponseEntity.ok(employeeService.findAll());
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
    public List<EmployeeDTO> employees() throws Exception {
        return employeeService.findAll();
    }

    @QueryMapping
    public EmployeeDTO employeeById(@Argument int id) throws Exception {
        return employeeService.findById(id);
    }

    // @SchemaMapping(field = "schedules", typeName = "Status")
    // public List<ScheduleDTO> schedules(StatusDTO status) throws Exception {
    //     return scheduleService.findAll(status.id());
    // }
}