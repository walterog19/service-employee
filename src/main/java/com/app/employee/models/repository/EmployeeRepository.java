package com.app.employee.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.employee.models.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	
	@Query("select e from Employee e  where e.person.name=:paramName")
	List<Employee> findListByName(@Param("paramName") String paramName);
	
	@Query("select e from Employee e where e.position.name=:paramNamePosition ")
	List<Employee> findListByPosition(@Param("paramNamePosition") String paramNamePosition);
	

	@Query("select e from Employee e where e.person.name=:paramName and e.position.name=:parNamePosition  ")
	List<Employee> findListByPositionAndName(@Param("parNamePosition") String parNamePosition, @Param("paramName") String paramName);

}
