package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employees extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "manager_id")
	@JsonIgnore
	private Employees employeeManagerId;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Column(name = "last_name")
	private String lastName;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "email", unique = true)
	private String email;

	@NotNull
	@Column(name = "phone_number")
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Departments department;

	@ManyToOne
	@JoinColumn(name = "position_id", nullable = false)
	private Positions position;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Projects project;

	@NotNull
	@Column(name = "salary")
	private Double Salary;

	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;

	@NotNull
	@Column(name = "dob")
	private LocalDate dob;

	@NotNull
	@Column(name = "address")
	private String address;
	
//	@Embedded
//	private Address address;

	@NotNull
	@Column(name = "gender")
	private Gender gender;

	@NotNull
	@Column(name = "qualification")
	private String qualification;

	@NotNull
	@Column(name = "date_of_joining")
	private LocalDate dateOfJoining;

	@Column(name = "date_of_leaving")
	private LocalDate dateOfLeaving;

}
