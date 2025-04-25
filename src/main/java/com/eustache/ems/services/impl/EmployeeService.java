package com.eustache.ems.services.impl;

import com.eustache.ems.dto.EmployeeDTO;
import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.errors.ResourceNotFoundException;
import com.eustache.ems.mapper.EmployeeMapper;
import com.eustache.ems.models.Employee;
import com.eustache.ems.repositories.EmployeeRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.eustache.ems.services.EmployeeServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService implements EmployeeServices {
    private final EmployeeRepository employeeRepository;
    //private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponseDTO(savedEmployee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with the given id: " + id));
        return employeeMapper.toEmployeeResponseDTO(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Integer id, EmployeeDTO updatedEmployeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with the given id: " + id));
        employee.setFirstname(updatedEmployeeDTO.firstname());
        employee.setLastname(updatedEmployeeDTO.lastname());
        employee.setEmail(updatedEmployeeDTO.email());
        employee.setPhone(updatedEmployeeDTO.phone());
        employee.setPassword(updatedEmployeeDTO.password());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponseDTO(savedEmployee);
    }


    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with id: " + id));
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toEmployeeResponseDTO)
                .collect(Collectors.toList());
    }
}
