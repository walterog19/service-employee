package com.app.employee.service;

import java.util.List;

import com.app.employee.models.entity.Person;


public interface IPersonService {
	
	Person getPersonById(long id);
	
	List<Person> getPersons();

	void insert(Person person)  throws Exception;

	void update(Person person) throws Exception;

    void delete(long id);

    boolean isPersonExist(long id);

}
