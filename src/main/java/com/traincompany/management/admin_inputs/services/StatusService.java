package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.models.Status;
import com.traincompany.management.admin_inputs.repositories.StatusRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;
    private final Mapper mapper;

    public List<StatusDTO> findAll() throws Exception {
        try {
            var dbStatus = statusRepository.findAll();
            var statusList = dbStatus.stream().map(status -> mapper.map(status)).toList();

            return statusList;
        } catch (Exception ex) {
            log.error("Error at getting status: {}", ex.getMessage());
            throw new Exception("Error at getting status");
        }
    }

    public StatusDTO findById(Integer id) throws Exception {
        try {
            Status dbStatus = statusRepository.findById(id).orElseThrow(() -> new Exception("Status not found"));
            var status = mapper.map(dbStatus);
            
            return status;
        } catch (Exception ex) {
            log.error("Error at getting status: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public StatusDTO save(StatusDTO status) throws Exception {
        try {
            if(status.id() == null) {
                Status statusToSave = mapper.map(status);
                statusToSave = statusRepository.save(statusToSave);

                return mapper.map(statusToSave);
            } else {
                Status statusToUpdate = statusRepository.findById(status.id()).get();
                statusToUpdate.setName(status.name());
                statusToUpdate.setDescription(status.description());

                statusToUpdate = statusRepository.save(statusToUpdate);

                return mapper.map(statusToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving status: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Status statusToDelete = statusRepository.findById(id).get();

            statusRepository.delete(statusToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting status with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}