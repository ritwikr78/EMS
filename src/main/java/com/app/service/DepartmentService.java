package com.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Departments;
import com.app.entity.Employees;
import com.app.repo.DepartmentRepository;
import com.app.repo.EmployeeRepository;

@Service
@Transactional
public class DepartmentService {

	@Autowired
	private DepartmentRepository DepartmentRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	// method to get all position
	public List<Departments> getAllDepartments() {
		return DepartmentRepo.findAll();
	}

	// method to get position by id
	public Departments getDepartmentsById(Long id) {
		return DepartmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Department By Id Not found !"));
	}

	// add position
	public Map<String, String> addDepartments(Departments department) {
		DepartmentRepo.save(department);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Department Added Successfully");
		return respMap;
	}

	// update position
	public Map<String, String> updateDepartments(Departments departments) {
		Departments departmentsById = this.getDepartmentsById(departments.getId());
		departmentsById.setDepartmentName(departments.getDepartmentName());

		DepartmentRepo.save(departmentsById);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Department Updated Successfully !");
		return respMap;

	}

	// Delete Position
	public Map<String, String> deleteDepartments(Long deptId) {
		Departments departmentById = this.getDepartmentsById(deptId);

		List<Employees> findByDepartment = employeeRepo.findByDepartment(departmentById);

		Map<String, String> respMap = new HashMap<String, String>();
		if (findByDepartment.isEmpty()) {
			DepartmentRepo.delete(departmentById);
			respMap.put("message", "Department Deleted Successfully !");
		} else {
			respMap.put("message", "Department Cannot be deleted, it has been assigned to employees");
		}
		return respMap;
	}
}
