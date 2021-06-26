package com.app.employee.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employee.models.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
