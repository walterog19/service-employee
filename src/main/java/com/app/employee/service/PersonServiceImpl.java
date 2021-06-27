package com.app.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.employee.models.entity.Person;
import com.app.employee.models.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private PersonRepository personeRepository;

	@Override
	@Transactional(readOnly = true)
	public Person getPersonById(long id) {

		if (log.isInfoEnabled()) {
			log.info("getPersonById : " + id);
		}

		return personeRepository.findById(id).orElse(null);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Person> getPersons() {
		if (log.isInfoEnabled()) {
			log.info("getPersons");
		}
		return personeRepository.findAll();
	}

	@Override
	public void insert(Person person) throws Exception {

		personeRepository.saveAndFlush(person);
	}

	@Override
	public void update(Person person) throws Exception {

		if (person.getId() == null) {
			if (log.isWarnEnabled()) {
				log.error("Error person without id.");
			}
			// TODO It should create a custom exception, I was not made by time
			throw new Exception("Error person without id.");
		}

		personeRepository.saveAndFlush(person);

	}

	@Override
	public void delete(long id) {

		personeRepository.deleteById(id);
	}

	@Override
	public boolean isPersonExist(long id) {
		return personeRepository.existsById(id);
	}

}
