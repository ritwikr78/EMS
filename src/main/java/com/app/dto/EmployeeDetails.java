package com.app.dto;

import com.app.entity.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDetails {

	private Long id;
	private ApprovalStatus status;
}
