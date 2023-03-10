package com.app.dto;

import com.app.entity.Employees;

public class JwtResponse {

	private Employees employee;
	private String jwtToken;

	public JwtResponse(Employees employee, String jwtToken) {
		super();
		this.employee = employee;
		this.jwtToken = jwtToken;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
