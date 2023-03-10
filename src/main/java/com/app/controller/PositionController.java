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

import com.app.entity.Positions;
import com.app.service.PositionService;

@RestController
@RequestMapping("/api/position")
public class PositionController {

	@Autowired
	private PositionService positonService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllPositions() {
		return ResponseEntity.status(HttpStatus.OK).body(positonService.getAllPositions());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPositionsById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(positonService.getPositionsById(id));
	}

	@PostMapping
	public ResponseEntity<?> addPositions(@RequestBody @Valid Positions position) {
		return ResponseEntity.status(HttpStatus.OK).body(positonService.addPosition(position));
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deletePositions(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(positonService.deletePosition(id));
	}

	@PutMapping
	public ResponseEntity<?> updateProject(@RequestBody @Valid Positions positions) {
		return ResponseEntity.status(HttpStatus.OK).body(positonService.updatePosition(positions));
	}
}
