package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.StationDTO;
import com.traincompany.management.admin_inputs.models.Station;
import com.traincompany.management.admin_inputs.repositories.StationRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StationService {
    private final StationRepository stationRepository;
    private final Mapper mapper;

    public List<StationDTO> findAll() throws Exception {
        try {
            var dbStations = stationRepository.findAll();
            var stationsList = dbStations.stream().map(station -> mapper.map(station)).toList();

            return stationsList;
        } catch (Exception ex) {
            log.error("Error at getting stations: {}", ex.getMessage());
            throw new Exception("Error at getting stations");
        }
    }

    public StationDTO findById(Integer id) throws Exception {
        try {
            Station dbStation = stationRepository.findById(id).orElseThrow(() -> new Exception("Station not found"));
            var station = mapper.map(dbStation);
            
            return station;
        } catch (Exception ex) {
            log.error("Error at getting station: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    public StationDTO save(StationDTO station) throws Exception {
        try {
            if(station.id() == null) {
                Station stationToSave = mapper.map(station);
                stationToSave = stationRepository.save(stationToSave);

                return mapper.map(stationToSave);
            } else {
                Station stationToUpdate = stationRepository.findById(station.id()).get();
                stationToUpdate.setName(station.name());
                stationToUpdate.setCityId(station.cityId());
                stationToUpdate.setCountryCode(station.countryCode());
                stationToUpdate.setPhone(station.phone());
                stationToUpdate.setPostalCode(station.postalCode());
                stationToUpdate.setLatitude(station.latitude());
                stationToUpdate.setLongitude(station.longitude());
                stationToUpdate.setImageUrl(station.imageUrl());

                stationToUpdate = stationRepository.save(stationToUpdate);

                return mapper.map(stationToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving station: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Station stationToDelete = stationRepository.findById(id).get();

            stationRepository.delete(stationToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting station with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}
