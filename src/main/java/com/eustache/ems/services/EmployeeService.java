package com.eustache.ems.services;

import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.errors.NotFoundException;
import com.eustache.ems.mapper.EmployeeMapper;
import com.eustache.ems.models.Employee;
import com.eustache.ems.repositories.EmployeeRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    //Get All employee
    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toEmployeeResponseDTO)
                .collect(Collectors.toList());
    }

    //Get by ID
    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
    }

    //To create an Employee
    public Employee creteEmployee(Employee employee) {
        return employeeRepository.save(employee);
//        employee.setPassword(encoder.encode(employee.getPassword()));
    }

    //To delete an employee
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    //To update en employee
    public EmployeeResponseDTO update(Integer id, Employee employee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setFirstname(employee.getFirstname());
                    existingEmployee.setLastname(employee.getLastname());
                    existingEmployee.setEmail(employee.getEmail());
                    existingEmployee.setPhone(employee.getPhone());
                    existingEmployee.setPassword(employee.getPassword());
                    return employeeRepository.save(existingEmployee);
                })
                .map(employeeMapper::toEmployeeResponseDTO)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
}
