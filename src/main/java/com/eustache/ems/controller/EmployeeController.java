package com.eustache.ems.controller;

import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.models.Employee;
import com.eustache.ems.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index(){
        return "Welcome to the Employee Management System";
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.creteEmployee(employee);
    }

    @GetMapping("/employee")
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
