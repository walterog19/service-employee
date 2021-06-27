package com.app.employee.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionResponseDTO {
	private Long id;
	private String name;
	List<EmployeeDTO> employees;
	
	

}
