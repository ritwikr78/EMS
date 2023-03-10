package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attendance {
	private Long employeeId;
	private String date;
	private String inTime;
	private String outTime;
}
