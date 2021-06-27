package com.app.employee.util;

import java.util.List;
import java.util.stream.Collectors;

import  com.app.employee.models.dto.EmployeeDTO;
import  com.app.employee.models.dto.PersonDTO;
import com.app.employee.models.entity.Employee;

public final class UtilMapEmployee {

	private UtilMapEmployee() {
		super();
	}

	/**
	 * Méthood  return a list of objects EmployeeDTO
	 * input   Employee
	 * 
	 * @param  List<Employee>
	 * @return List<EmployeeDTO>
	 */
	public static List<EmployeeDTO> employeeToEmployeeDTO( List<Employee> employees) {
		
		return employees.stream().map(val -> {
			
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setId(val.getId());
			employeeDTO.setSalary(val.getSalary());
			PersonDTO personDTO  = new PersonDTO();
			personDTO.setName(val.getPerson().getName());
			personDTO.setLastName(val.getPerson().getLastName());
			personDTO.setAddress(val.getPerson().getAddress());
			personDTO.setCellphone(val.getPerson().getCellphone());
			personDTO.setCityName(val.getPerson().getCityName());
			employeeDTO.setPerson(personDTO);
			return employeeDTO;
			
		}).collect(Collectors.toList());	
		
		
	}

}
