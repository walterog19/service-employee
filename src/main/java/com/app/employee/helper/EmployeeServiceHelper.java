package com.app.employee.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.app.employee.models.entity.Employee;
import com.app.employee.models.entity.Person;
import com.app.employee.models.entity.Position;

public class EmployeeServiceHelper {
	
	
	public static Employee createEmployee() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setSalary(1500D);
		Person person =  new Person();
		person.setId(1L);
		person.setName("Walter");
		person.setLastName("Osorio");
		person.setAddress("Cra 10 10181");
		person.setCellphone("316661111");
		person.setCityName("Pereira");
		employee.setPerson(person);
		Position position = new Position();
		position.setId(1L);
		position.setName("dev");
		employee.setPosition(position);
		
		
		return employee;
	}

	public static List<Employee> createEmployees() {
		return Arrays.asList(createEmployee());
	}
	
	public static List<Employee> createEmployeeEmpty() {
		return new ArrayList<>();
	}


}
