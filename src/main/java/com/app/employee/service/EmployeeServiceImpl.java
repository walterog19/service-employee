package com.app.employee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.employee.models.entity.Employee;
import com.app.employee.models.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional(readOnly = true)
	public Employee getEmployeeById(long id) {

		if (log.isInfoEnabled()) {
			log.info("getEmployeeById : " + id);
		}

		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> getEmployees() {
		if (log.isInfoEnabled()) {
			log.info("getEmployees");
		}
		return employeeRepository.findAll();
	}

	@Override
	public void insert(Employee employee) throws Exception {
		if (employee.getPerson() == null) {
			if (log.isWarnEnabled()) {
				log.error("Error employee without person.");
			}
			// TODO It should create a custom exception, I was not made by time
			throw new Exception("Error employee without person.");
		}
		employeeRepository.saveAndFlush(employee);

	}

	@Override
	public void update(Employee employee) throws Exception {
		if (employee.getId() == null) {
			if (log.isWarnEnabled()) {
				log.error("Error employee without id.");
			}
			// TODO It should create a custom exception, I was not made by time
			throw new Exception("Error employee without id.");
		}

		employeeRepository.saveAndFlush(employee);

	}

	@Override
	public void delete(long id) {

		employeeRepository.deleteById(id);

	}

	@Override
	public boolean isEmployeeExist(long id) {

		return employeeRepository.existsById(id);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return employeeRepository.findListByName(name);
	}

	@Override
	public List<Employee> getEmployeesByPosition(String position) {
		return employeeRepository.findListByPosition(position);
	}

	@Override
	public List<Employee> getEmployeesByPositionAndName(String position, String name) {
		return employeeRepository.findListByPositionAndName(position, name);
	}

}
