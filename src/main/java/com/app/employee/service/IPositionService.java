package com.app.employee.service;

import java.util.List;

import com.app.employee.models.entity.Position;

public interface IPositionService {
	
	
	Position getPositionById(long id);
	
	List<Position> getPositions();

	void insert(Position position) throws Exception;

	void update(Position position)throws Exception;

    void delete(long id);

    boolean isPositionExist(long id);
    
    boolean isPositionExist(String name);

}
