package com.eustache.ems.dto;

public record EmployeeResponseDTO(
        String firstname,
        String lastname,
        String email,
        String phone
) {
}
