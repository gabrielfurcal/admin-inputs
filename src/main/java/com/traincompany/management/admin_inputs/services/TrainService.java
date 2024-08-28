package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.TrainDTO;
import com.traincompany.management.admin_inputs.models.Train;
import com.traincompany.management.admin_inputs.repositories.TrainRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainService {
    private final TrainRepository trainRepository;
    private final Mapper mapper;

    public List<TrainDTO> findAll() throws Exception {
        try {
            var dbTrains = trainRepository.findAll();
            var trainsList = dbTrains.stream().map(train -> mapper.map(train)).toList();

            return trainsList;
        } catch (Exception ex) {
            log.error("Error at getting trains: {}", ex.getMessage());
            throw new Exception("Error at getting trains");
        }
    }

    public TrainDTO findById(int id) throws Exception {
        try {
            Train dbTrain = trainRepository.findById(id).orElseThrow(() -> new Exception("Train not found"));
            var train = mapper.map(dbTrain);
            
            return train;
        } catch (Exception ex) {
            log.error("Error at getting train: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}