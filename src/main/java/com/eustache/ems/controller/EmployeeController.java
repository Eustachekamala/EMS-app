package com.eustache.ems.controller;

import com.eustache.ems.dto.EmployeeDTO;
import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.services.impl.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    //Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO
    ) {
        EmployeeResponseDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    //Get all Employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    //Get by ID
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeByBid(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    //Update Employee
    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Integer id,
            @Valid @RequestBody  EmployeeDTO employeeDTO
    ){
        EmployeeResponseDTO savedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(savedEmployee);
    }

    //Delete Employee
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Integer id
    ){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted Successfully");
    }
}
