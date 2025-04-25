package com.eustache.ems.services;

import com.eustache.ems.dto.EmployeeDTO;
import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.models.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeServices {
    EmployeeResponseDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeResponseDTO getEmployeeById(Integer id);
    EmployeeResponseDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);
    void deleteEmployee(Integer id);
    List<EmployeeResponseDTO> getAllEmployees();
}
