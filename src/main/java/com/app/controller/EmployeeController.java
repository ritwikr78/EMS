package com.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.Attendance;
import com.app.dto.EmployeeDataInsertion;
import com.app.dto.EmployeeDetails;
import com.app.entity.ApprovalStatus;
import com.app.entity.EmployeeAttendance;
import com.app.entity.Employees;
import com.app.service.EmployeeAttendanceService;
import com.app.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public EmployeeAttendanceService empAttendanceService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployeesDetails());
	}

	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDataInsertion emp) {

		Employees empFoundInRecords = null;

		if (emp.getEmail() != null)
			empFoundInRecords = employeeService.findEmployeeByEmail(emp.getEmail());

		if (empFoundInRecords == null)
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.addEmployeeDetails(emp));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee Details Already Present");
	}

	@PutMapping
	public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employees emp) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployeeDetails(emp));
	}

	@PostMapping("/addManager")
	public ResponseEntity<?> addEmployeeManager(@RequestBody HashMap<String, Long> empMgrMap) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.addEmployeeManager(empMgrMap));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeesDetailsById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeesDetailsById(id));
	}

	@GetMapping("/{id}/manager")
	public ResponseEntity<?> getEmployeeManagerDetails(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getManagerEmployeeDetails(id));
	}

	@PostMapping("/{id}/delete")
	public ResponseEntity<?> leftEmployeeUpdateRecord(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.leftEmployeeUpdateRecord(id));
	}

	@PostMapping("/addProject")
	public ResponseEntity<?> addEmployeeProject(@RequestBody @Valid HashMap<String, Long> empProjectMap) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.addEmployeeProject(empProjectMap));
	}

	@PostMapping("/markAttendance")
	public ResponseEntity<?> markAttendance(@RequestBody @Valid Attendance attendance) {
		EmployeeAttendance empAttendance = new EmployeeAttendance();
		// employee Id
		empAttendance.setEmployeeId(employeeService.getEmployeesDetailsById(attendance.getEmployeeId()));

		// parse date
		empAttendance.setDate(LocalDate.parse(attendance.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

		// parse InTime
		String inTime = empAttendance.getDate() + " " + attendance.getInTime();
		empAttendance.setInTime(LocalDateTime.parse(inTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		// parse OutTime
		String outTime = empAttendance.getDate() + " " + attendance.getOutTime();
		empAttendance.setOutTime(LocalDateTime.parse(outTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

		// Mark Status
		empAttendance.setApprovalStatus(ApprovalStatus.PENDING);

		return ResponseEntity.status(HttpStatus.OK).body(empAttendanceService.addEmployeeAttendance(empAttendance));
	}

	@GetMapping("/approveAttendance/{status}")
	public ResponseEntity<?> showPendingAttendanceToManager(@PathVariable ApprovalStatus status) {
		return ResponseEntity.status(HttpStatus.OK).body(empAttendanceService.getPendingAttendance(status));
	}

	@PostMapping("/approveAttendance")
	public ResponseEntity<?> updateAttendance(@RequestBody @Valid EmployeeDetails emp) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(empAttendanceService.updateAttendance(emp.getId(), emp.getStatus()));
	}

	@GetMapping("/{id}/showAttendance")
	public ResponseEntity<?> showAttendance(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(empAttendanceService.getAttendanceById(id));
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> showAttendance(@RequestBody HashMap<String, String> mapEmailPassword) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.changePassword(mapEmailPassword));
	}

	@GetMapping("/{id}/showAttendance/{status}")
	public ResponseEntity<?> showAttendanceByType(@PathVariable Long id, @PathVariable ApprovalStatus status) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(empAttendanceService.getPendingAttendanceListByManagerId(id, status));
	}
	@GetMapping("/showAllAttendance")
	public ResponseEntity<?> getAllAttendance() {
		return ResponseEntity.status(HttpStatus.OK).body(empAttendanceService.getAllAttendance());
	}

}
