package com.eustache.ems.dto;

import jakarta.validation.constraints.NotEmpty;

public record EmployeeDTO(
        Integer id,
        @NotEmpty(message = "Firstname should not be empty")
        String firstname,
        @NotEmpty(message = "Lastname should not be empty")
        String lastname,
        @NotEmpty(message = "Email should not be empty")
        String email,
        @NotEmpty(message = "Password should not be empty")
        String password,
        @NotEmpty(message = "Phone should not be empty")
        String phone
) {
}
