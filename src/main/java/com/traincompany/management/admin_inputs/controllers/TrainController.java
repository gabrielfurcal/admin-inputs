package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.traincompany.management.admin_inputs.DTOs.TrainDTO;
import com.traincompany.management.admin_inputs.services.TrainService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/trains/")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping()
    public ResponseEntity<List<TrainDTO>> getTrains() {
        try {
            return ResponseEntity.ok(trainService.findAll());
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
    public List<TrainDTO> trains() throws Exception {
        return trainService.findAll();
    }

    @QueryMapping
    public TrainDTO trainById(@Argument Integer id) throws Exception {
        return trainService.findById(id);
    }

    @MutationMapping
    public TrainDTO saveTrain(@Argument TrainDTO train) throws Exception {
        return trainService.save(train);
    }

    @MutationMapping
    public Boolean deleteTrain(@Argument Integer id) throws Exception {
        return trainService.deleteById(id);
    }
}