package com.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeAttendance extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employees employeeId;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Column(name = "in_time", nullable = false)
	private LocalDateTime inTime;

	@Column(name = "out_time", nullable = false)
	private LocalDateTime outTime;

	@Column(name = "approval_status")
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus;
}
