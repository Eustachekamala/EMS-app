package com.eustache.ems.repositories;

import com.eustache.ems.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    void deleteById(Integer id);
}
