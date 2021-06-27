package com.app.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.employee.service.IEmployeeService;
import com.app.employee.service.IPersonService;
import com.app.employee.service.IPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import com.app.employee.models.dto.PositionResponseDTO;
import com.app.employee.models.entity.Employee;
import com.app.employee.models.entity.Person;
import com.app.employee.models.entity.Position;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private IPersonService personService;


	@GetMapping(value = "/employees/{name}/{position}", headers = "Accept=application/json")
	public ResponseEntity<List<Employee>> listEmployees(@PathVariable("name") String name,
			@PathVariable("position") String position) {

		if (log.isInfoEnabled()) {
			log.info("Search by name {} and position {} ", name, position);
		}
		List<Employee> employees = employeeService.getEmployeesByPositionAndName(position, name);

		if (employees.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(employees, HttpStatus.OK);

	}

	@GetMapping(value = "/employees/{name}", headers = "Accept=application/json")
	public ResponseEntity<List<Employee>> listEmployees(@PathVariable("name") String name) {

		if (log.isInfoEnabled()) {
			log.info("Search by name {}  ", name);
		}
		List<Employee> employees = employeeService.getEmployeesByName(name);

		if (employees.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(employees, HttpStatus.OK);

	}

	@GetMapping(value = "/employees", headers = "Accept=application/json")
	public ResponseEntity<List<Employee>> listEmployees() {

		if (log.isInfoEnabled()) {
			log.info("Search all employees  ");
		}
		List<Employee> employees = employeeService.getEmployees();

		if (employees.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(employees, HttpStatus.OK);

	}

	@GetMapping(value = "/employees/position/{position}", headers = "Accept=application/json")
	public ResponseEntity<List<Employee>> listEmployeesByPosition(@PathVariable("position") String position) {

		if (log.isInfoEnabled()) {
			log.info("Search by position {}  ", position);
		}
		List<Employee> employees = employeeService.getEmployeesByPosition(position);

		if (employees.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(employees, HttpStatus.OK);

	}

	@PostMapping(value = "/employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception {

		if (log.isInfoEnabled()) {
			log.info("Create employee: {}", employee);
		}

		if (employee == null || employee.getPerson() == null || employee.getPosition() == null) {
			if (log.isWarnEnabled()) {
				log.error(" Employee need person and position requiere.", employee);
			}

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Person personSave = personService.insert(employee.getPerson());
		employee.setPerson(personSave);
		employee.setPosition(positionService.isPositionExist(employee.getPosition().getName())?
				employee.getPosition():
				positionService.insert(employee.getPosition()));
		employeeService.insert(employee);

		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee)
			throws Exception {
		//TODO  Uses UtilLog to clean code
		if (log.isInfoEnabled()) {
			log.info(" Update employee with id  {}", id);
		}

		Employee currentEmployee = employeeService.getEmployeeById(id);

		if (currentEmployee == null) {
			if (log.isWarnEnabled()) {
				log.error("Employee with  id {} not found.", id);
			}

			throw new Exception("Employee with  id: " + id + " not found.");
		}
		currentEmployee.setSalary(employee.getSalary());
		if (employee.getPerson() != null) {		
			employee.getPerson().setId(currentEmployee.getPerson().getId());
			Person personModify  = personService.update(employee.getPerson());
			currentEmployee.setPerson(personModify);
		}
		if (employee.getPosition() != null)	{	
		currentEmployee.setPosition(employee.getPosition());
		}

		employeeService.update(currentEmployee);

		return new ResponseEntity<>(currentEmployee, HttpStatus.OK);
	}

	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) throws Exception {

		if (log.isInfoEnabled()) {
			log.info("Search & delete eployee with id {}", id);
		}
		Employee employee = employeeService.getEmployeeById(id);

		if (employee == null) {
			if (log.isWarnEnabled()) {
				log.error(" I can't delete employee with id {} not found.", id);
			}

			throw new Exception("I can't delete employee with id " + id + " not found.");
		}
		employeeService.delete(id);
		return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/positions", headers = "Accept=application/json")
	public ResponseEntity<List<PositionResponseDTO>> listPositions() {

		List<PositionResponseDTO> positions = positionService.getPositionsReport();

		if (positions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(positions, HttpStatus.OK);

	}

}
