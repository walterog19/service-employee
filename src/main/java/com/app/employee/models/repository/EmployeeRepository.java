package com.app.employee.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employee.models.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
