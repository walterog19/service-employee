package com.app.employee.models.dto;

import java.io.Serializable;

import com.app.employee.models.dto.PersonDTO.PersonDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO implements Serializable {
	
	
	private Long id;
	private String name;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2746148127100020176L;

}
