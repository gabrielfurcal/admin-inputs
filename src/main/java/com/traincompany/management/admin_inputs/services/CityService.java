package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.CityDTO;
import com.traincompany.management.admin_inputs.models.City;
import com.traincompany.management.admin_inputs.repositories.CityRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {
    private final CityRepository cityRepository;
    private final Mapper mapper;

    public List<CityDTO> findAll() throws Exception {
        try {
            var dbCities = cityRepository.findAll();
            var citiesList = dbCities.stream().map(city -> mapper.map(city)).toList();

            return citiesList;
        } catch (Exception ex) {
            log.error("Error at getting cities: {}", ex.getMessage());
            throw new Exception("Error at getting cities");
        }
    }

    public CityDTO findById(Integer id) throws Exception {
        try {
            City dbCity = cityRepository.findById(id).orElseThrow(() -> new Exception("City not found"));
            var city = mapper.map(dbCity);
            
            return city;
        } catch (Exception ex) {
            log.error("Error at getting city: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public CityDTO save(CityDTO city) throws Exception {
        try {
            if(city.id() == null) {
                City cityToSave = mapper.map(city);
                cityToSave = cityRepository.save(cityToSave);

                return mapper.map(cityToSave);
            } else {
                City cityToUpdate = cityRepository.findById(city.id()).get();
                cityToUpdate.setCity(city.city());
                cityToUpdate.setProvince(city.province());
                cityToUpdate.setCountry(city.country());

                cityToUpdate = cityRepository.save(cityToUpdate);

                return mapper.map(cityToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving city: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            City cityToDelete = cityRepository.findById(id).get();

            cityRepository.delete(cityToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting city with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}