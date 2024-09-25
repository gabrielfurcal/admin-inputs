package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs.models.Schedule;
import com.traincompany.management.admin_inputs.utils.DateFormatter;
import com.traincompany.management.admin_inputs.utils.Mapper;
import com.traincompany.management.admin_inputs.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final Mapper mapper;

    public List<ScheduleDTO> findAll() throws Exception {
        try {
            var dbSchedules = scheduleRepository.findAll();
            var scheduleList = dbSchedules.stream().map(schedule -> mapper.map(schedule)).toList();
            
            return scheduleList;
        } catch (Exception ex) {
            log.error("Error at getting schedules: {}", ex.getMessage());
            throw new Exception("Error at getting schedules");
        }
    }

    public List<ScheduleDTO> findAll(Integer statusId) throws Exception {
        try {
            var dbSchedules = scheduleRepository.findAllByStatusId(statusId);
            var scheduleList = dbSchedules.stream().map(schedule -> mapper.map(schedule)).toList();

            return scheduleList;
        } catch (Exception ex) {
            log.error("Error at getting schedules: {}", ex.getMessage());
            throw new Exception("Error at getting schedules");
        }
    }

    public ScheduleDTO findById(Integer id) throws Exception {
        try {
            Schedule dbSchedule = scheduleRepository.findById(id).orElseThrow(() -> new Exception("Schedule not found"));
            var schedule = mapper.map(dbSchedule);
            
            return schedule;
        } catch (Exception ex) {
            log.error("Error at getting schedule: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public ScheduleDTO save(ScheduleDTO schedule) throws Exception {
        try {
            if(schedule.id() == null) {
                Schedule scheduleToSave = mapper.map(schedule);
                scheduleToSave = scheduleRepository.save(scheduleToSave);

                return mapper.map(scheduleToSave);
            } else {
                Schedule scheduleToUpdate = scheduleRepository.findById(schedule.id()).get();
                scheduleToUpdate.setDepartureTime(DateFormatter.toDate(schedule.departureTime(), "yyyy-MM-dd hh:mm:ss"));
                scheduleToUpdate.setArrivalTime((DateFormatter.toDate(schedule.arrivalTime(), "yyyy-MM-dd hh:mm:ss")));
                scheduleToUpdate.setRouteId(schedule.routeId());
                scheduleToUpdate.setStatusId(schedule.statusId());
                scheduleToUpdate.setTrainId(schedule.trainId());

                scheduleToUpdate = scheduleRepository.save(scheduleToUpdate);

                return mapper.map(scheduleToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving schedule: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Schedule scheduleToDelete = scheduleRepository.findById(id).get();

            scheduleRepository.delete(scheduleToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting schedule with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}