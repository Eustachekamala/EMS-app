package com.eustache.ems.mapper;

import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.models.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getFirstname(), employee.getLastname(), employee.getEmail(), employee.getPhone());
    }
}
