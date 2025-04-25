package com.eustache.ems.mapper;

import com.eustache.ems.dto.EmployeeDTO;
import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.models.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employee toEmployee(EmployeeDTO dto) {
        var employee = new Employee();
        employee.setId(dto.id());
        employee.setFirstname(dto.firstname());
        employee.setLastname(dto.lastname());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setPassword(dto.password());
        return employee;
    }

    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getId(),employee.getFirstname(), employee.getLastname(), employee.getEmail(), employee.getPhone());
    }
}
