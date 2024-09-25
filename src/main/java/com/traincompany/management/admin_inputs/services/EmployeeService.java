package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.EmployeeDTO;
import com.traincompany.management.admin_inputs.models.Employee;
import com.traincompany.management.admin_inputs.repositories.EmployeeRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Mapper mapper;

    public List<EmployeeDTO> findAll() throws Exception {
        try {
            var dbEmployees = employeeRepository.findAll();
            var employeesList = dbEmployees.stream().map(employee -> mapper.map(employee)).toList();

            return employeesList;
        } catch (Exception ex) {
            log.error("Error at getting employees: {}", ex.getMessage());
            throw new Exception("Error at getting employees");
        }
    }

    public EmployeeDTO findById(Integer id) throws Exception {
        try {
            Employee dbEmployee = employeeRepository.findById(id).orElseThrow(() -> new Exception("Employee not found"));
            var employee = mapper.map(dbEmployee);
            
            return employee;
        } catch (Exception ex) {
            log.error("Error at getting employee: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public EmployeeDTO save(EmployeeDTO employee) throws Exception {
        try {
            if(employee.id() == null) {
                Employee employeeToSave = mapper.map(employee);
                employeeToSave = employeeRepository.save(employeeToSave);

                return mapper.map(employeeToSave);
            } else {
                Employee employeeToUpdate = employeeRepository.findById(employee.id()).get();
                employeeToUpdate.setFirstName(employee.firstName());
                employeeToUpdate.setLastName(employee.lastName());
                employeeToUpdate.setEmail(employee.email());
                employeeToUpdate.setPhoneNumber(employee.phoneNumber());
                employeeToUpdate.setPosition(employee.position());

                employeeToUpdate = employeeRepository.save(employeeToUpdate);

                return mapper.map(employeeToUpdate);
            }
        } catch (Exception ex) {
            log.error("Error at saving employee: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Employee employeeToDelete = employeeRepository.findById(id).get();

            employeeRepository.delete(employeeToDelete);

            return true;
        } catch(Exception ex) {
            log.error("Error at deleting employee with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}