package com.app.employee.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.employee.models.entity.Position;
import com.app.employee.models.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PositionServiceImpl implements IPositionService {
	@Autowired
	private PositionRepository positionRepository;

	@Override
	@Transactional(readOnly = true)
	public Position getPositionById(long id) {
		if (log.isInfoEnabled()) {
			log.info("getPositionById : " + id);
		}
		return positionRepository.findById(id).orElse(null);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<Position> getPositions() {
		if (log.isInfoEnabled()) {
			log.info("getPositions");
		}
		return positionRepository.findAll();
	}

	@Override
	public void insert(Position position) throws Exception {
		
		positionRepository.saveAndFlush(position);
		
	}

	@Override
	public void update(Position position) throws Exception{
		if (position.getId() == null) {
			if (log.isWarnEnabled()) {
				log.error("Error position without id.");
			}
			// TODO It should create a custom exception, I was not made by time
			throw new Exception("Error position without id.");
		}

		positionRepository.saveAndFlush(position);
		
	}

	@Override
	public void delete(long id) {
		positionRepository.deleteById(id);
		
	}

	@Override
	public boolean isPositionExist(long id) {
		return positionRepository.existsById(id);
	}

	@Override
	public boolean isPositionExist(String name) {
		return positionRepository.findByName(name) != null && !positionRepository.findByName(name).getName().isEmpty();
	}
	

}
