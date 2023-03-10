package com.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Employees;
import com.app.entity.Positions;
import com.app.repo.EmployeeRepository;
import com.app.repo.PositionRepository;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepository positionRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	// method to get all position
	public List<Positions> getAllPositions() {
		return positionRepo.findAll();
	}

	// method to get position by id
	public Positions getPositionsById(Long id) {
		return positionRepo.findById(id).orElseThrow(() -> new RuntimeException("Position By Id Not found !"));
	}

	// add position
	public Map<String, String> addPosition(Positions position) {
		positionRepo.save(position);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Position Added Successfully");
		return respMap;
	}

	// update position
	public Map<String, String> updatePosition(Positions position) {
		Positions positionsById = this.getPositionsById(position.getId());

		positionsById.setPositionName(position.getPositionName());
		positionsById.setRole(position.getRole());

		positionRepo.save(positionsById);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Position Updated Successfully !");
		return respMap;

	}

	// Delete Position
	public Map<String, String> deletePosition(Long posId) {
		Positions positionsById = this.getPositionsById(posId);

		List<Employees> findByPosition = employeeRepo.findByPosition(positionsById);

		Map<String, String> respMap = new HashMap<String, String>();
		if (findByPosition.isEmpty()) {
			positionRepo.delete(positionsById);
			respMap.put("message", "Position Deleted Successfully !");
		} else {
			respMap.put("message", "Position Cannot be deleted, it has been assigned to employees");
		}
		return respMap;
	}
}
