package com.app.employee.service;

import java.util.List;

import com.app.employee.models.entity.Employee;

public interface IEmployeeService {
	
	Employee getEmployeeById(long id);
	
	List<Employee> getEmployees();

	void insert(Employee Employee)  throws Exception;

	void update(Employee Employee)  throws Exception;

    void delete(long id);

    boolean isEmployeeExist(long id);

}
