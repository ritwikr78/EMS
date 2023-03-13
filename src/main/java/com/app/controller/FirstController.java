package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.entity.Employees;
import com.app.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FirstController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<?> helloWorldController() {
		return ResponseEntity.status(HttpStatus.OK).body("Hello World");
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest Login) {

		Employees emp = employeeService.authenticateEmployee(Login);

		if (emp != null)
			return ResponseEntity.status(HttpStatus.OK).body(emp);
		else
			throw new RuntimeException("Incorrect Email or Password");
	}
}
