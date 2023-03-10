package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Departments;
import com.app.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	@Autowired
	private DepartmentService DepartmentService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllProject() {
		return ResponseEntity.status(HttpStatus.OK).body(DepartmentService.getAllDepartments());
	}

	@PostMapping
	public ResponseEntity<?> addNewProject(@RequestBody @Valid Departments department) {
		return ResponseEntity.status(HttpStatus.OK).body(DepartmentService.addDepartments(department));
	}

	@PutMapping
	public ResponseEntity<?> updateProject(@RequestBody @Valid Departments department) {
		return ResponseEntity.status(HttpStatus.OK).body(DepartmentService.updateDepartments(department));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(DepartmentService.getDepartmentsById(id));
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deletePositions(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(DepartmentService.deleteDepartments(id));
	}
}
