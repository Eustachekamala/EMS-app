package com.eustache.ems.dto;

import org.springframework.http.ResponseEntity;

public record EmployeeResponseDTO(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String phone
) {
}
