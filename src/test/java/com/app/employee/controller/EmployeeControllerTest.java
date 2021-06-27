package com.app.employee.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.app.employee.helper.EmployeeServiceHelper;
import com.app.employee.service.IEmployeeService;
import com.app.employee.service.IPersonService;
import com.app.employee.service.IPositionService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = com.app.employee.controller.EmployeeController.class)
@TestPropertySource(value = "/application.properties")
public class EmployeeControllerTest {
	
	@MockBean
	private IEmployeeService employeeService;

	@MockBean
	private IPositionService positionService;
	
	@MockBean
	private IPersonService personService;
	
	@Autowired
	private MockMvc mockMvc;
	
	

	@Test
	public void testListAllEmployesOK() throws Exception {

		when(employeeService.getEmployees()).thenReturn(EmployeeServiceHelper.createEmployees());

		mockMvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void testListAllUsersNoContent() throws Exception {

		when(employeeService.getEmployees()).thenReturn(EmployeeServiceHelper.createEmployeeEmpty());

		mockMvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isNoContent());
	}
	

}
