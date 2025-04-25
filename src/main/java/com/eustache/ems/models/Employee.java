package com.eustache.ems.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "Firstname should not be empty")
    @Column(nullable = false)
    private String firstname;
    @NotEmpty(message = "Lastname should not be empty")
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Column(unique = true)
    private String phone;
    @NotEmpty(message = "Phone should not be empty")
    private String password;
}
