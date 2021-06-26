package com.app.employee.models.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

	private Long id;
	private Double salary;
	private PersonDTO person;
	private PositionDTO position;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036198392773338228L;

}
