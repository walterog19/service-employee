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
public class PersonDTO implements Serializable {


	
	private String name;
	private String lastName;
	private String address;
	private String cellphone;
	private String cityName;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5899255657609990557L;

}
