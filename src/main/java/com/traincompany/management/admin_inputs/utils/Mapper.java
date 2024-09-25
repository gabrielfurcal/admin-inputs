package com.traincompany.management.admin_inputs.utils;

import org.springframework.stereotype.Component;
import com.traincompany.management.admin_inputs.DTOs.CityDTO;
import com.traincompany.management.admin_inputs.DTOs.EmployeeDTO;
import com.traincompany.management.admin_inputs.DTOs.RouteDTO;
import com.traincompany.management.admin_inputs.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs.DTOs.StationDTO;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.DTOs.TrainDTO;
import com.traincompany.management.admin_inputs.models.City;
import com.traincompany.management.admin_inputs.models.Employee;
import com.traincompany.management.admin_inputs.models.Route;
import com.traincompany.management.admin_inputs.models.Schedule;
import com.traincompany.management.admin_inputs.models.Station;
import com.traincompany.management.admin_inputs.models.Status;
import com.traincompany.management.admin_inputs.models.Train;

@Component
public class Mapper {
    /*
    * Schedule Mappers
    */
    public ScheduleDTO map(Schedule schedule) {
        return new ScheduleDTO(
                    schedule.getId(), 
                    schedule.getTrainId(), 
                    schedule.getRouteId(),
                    schedule.getStatusId(),
                    DateFormatter.toString(schedule.getDepartureTime(), "yyyy-MM-dd hh:mm:ss"),
                    DateFormatter.toString(schedule.getArrivalTime(), "yyyy-MM-dd hh:mm:ss"),
                    null);
    }

    public Schedule map(ScheduleDTO schedule) throws Exception {
        return new Schedule(
                    schedule.id(), 
                    schedule.trainId(), 
                    schedule.routeId(),
                    schedule.statusId(),
                    DateFormatter.toDate(schedule.departureTime(), "yyyy-MM-dd hh:mm:ss"),
                    DateFormatter.toDate(schedule.arrivalTime(), "yyyy-MM-dd hh:mm:ss"),
                    null,
                    null,
                    null);
    }
    /*
    * Employee Mappers
    */
    public EmployeeDTO map(Employee employee) {
        return new EmployeeDTO(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getPosition(),
            employee.getPhoneNumber(),
            employee.getEmail(),
            null
        );
    }

    public Employee map(EmployeeDTO employee) {
        return new Employee(
            employee.id(),
            employee.firstName(),
            employee.lastName(),
            employee.position(),
            employee.phoneNumber(),
            employee.email()
        );
    }

    /*
    * Station Mappers
    */
    public StationDTO map(Station station) {
        return new StationDTO(
            station.getId(),
            station.getName(),
            station.getCountryCode(),
            station.getPhone(),
            station.getPostalCode(),
            station.getLatitude(),
            station.getLongitude(),
            station.getCityId(),
            station.getImageUrl(),
            null
        );
    }

    public Station map(StationDTO station) {
        return new Station(
            station.id(),
            station.name(),
            station.countryCode(),
            station.phone(),
            station.postalCode(),
            station.latitude(),
            station.longitude(),
            station.cityId(),
            station.imageUrl(),
            null
        );
    }

    /*
    * Route Mappers
    */
    public RouteDTO map(Route route) {
        return new RouteDTO(
            route.getId(),
            route.getStartStationId(),
            route.getEndStationId(),
            route.getDistance(),
            null
        );
    }

    public Route map(RouteDTO route) {
        return new Route(
            route.id(),
            route.startStationId(),
            route.endStationId(),
            route.distance(),
            null,
            null
        );
    }

    /*
    * Status Mappers
    */
    public StatusDTO map(Status status) {
        return new StatusDTO(status.getId(), status.getName(), status.getDescription());
    }

    public Status map(StatusDTO status) {
        return new Status(status.id(), status.name(), status.description());
    }

    /*
    * Train Mappers
    */
    public TrainDTO map(Train train) {
        return new TrainDTO(train.getId(), train.getType(), train.getCapacity(), train.getMaxSpeed());
    }

    public Train map(TrainDTO train) {
        return new Train(train.id(), train.type(), train.capacity(), train.maxSpeed());
    }

    /*
    * City Mappers
    */
    public CityDTO map(City city) {
        return new CityDTO(city.getId(), city.getCity(), city.getProvince(), city.getCountry());
    }

    public City map(CityDTO city) {
        return new City(city.id(), city.city(), city.province(), city.country());
    }
}