package com.app.employee.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employee.models.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
	
	Position findByName(String name);

}
