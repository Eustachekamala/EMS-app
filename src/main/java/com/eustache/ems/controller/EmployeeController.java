package com.eustache.ems.controller;

import com.eustache.ems.dto.EmployeeResponseDTO;
import com.eustache.ems.models.Employee;
import com.eustache.ems.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
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

    //Handle exception of type MethodArgumentNotValidException that occur when validation on a method
    // argument annotated with @valid fails.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        //create a map to store field names and their corresponding error message
        var errors = new HashMap<String, String>();

        //Iterate through all validation errors and populate the map
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField(); //Extract the field name
            var errorMessage = error.getDefaultMessage(); //Extract the error message
            errors.put(fieldName, errorMessage);
        });
        //Return the map of errors wrapped in a ResponseEntity with a BAD_REQUEST status
        return ResponseEntity.badRequest().body(errors);
    }
}
