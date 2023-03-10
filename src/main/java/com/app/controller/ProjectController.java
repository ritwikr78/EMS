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

import com.app.entity.Projects;
import com.app.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllProject() {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllprojects());
	}

	@PostMapping
	public ResponseEntity<?> addNewProject(@RequestBody @Valid Projects project) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.addNewProject(project));
	}

	@PutMapping
	public ResponseEntity<?> updateProject(@RequestBody @Valid Projects project) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(project));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectDetailsById(id));
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deletePositions(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.deleteProject(id));
	}
}
