package com.app.dto;

import com.app.entity.Address;
import com.app.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDataInsertion {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Long department_id;
	private Long position_id;
	private Double salary;
	private Boolean isActive;
	private String dob;
	private String address;
	private Gender gender;
	private String qualification;
	private String dateOfJoining;
}
