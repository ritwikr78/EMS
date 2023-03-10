package com.app.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ApprovalStatus;
import com.app.entity.EmployeeAttendance;
import com.app.entity.Employees;
import com.app.repo.EmployeeAttendanceRepository;
import com.app.repo.EmployeeRepository;

@Service
@Transactional
public class EmployeeAttendanceService {

	@Autowired
	private EmployeeAttendanceRepository empAttendanceRepo;

	@Autowired
	private EmployeeRepository empRepo;

	public Map<String, String> addEmployeeAttendance(EmployeeAttendance empAttendance) {

		if (empAttendance.getEmployeeId().getId() == null)
			throw new RuntimeException("Employee Id not found");
		else if (empAttendance.getDate().isAfter(LocalDate.now()))
			throw new RuntimeException(" Date Cannot be future date !");
		else if (empAttendance.getInTime().isAfter(empAttendance.getOutTime()))
			throw new RuntimeException(" In time cannot be greater than out time");

		empAttendanceRepo.save(empAttendance);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Attendance Added Successfully, Wait for Manager Approval");
		return respMap;

	}

	public List<EmployeeAttendance> getPendingAttendance(ApprovalStatus status) {
		return empAttendanceRepo.findByApprovalStatus(status);

	}

	public Map<String, String> updateAttendance(Long id, ApprovalStatus status) {
		EmployeeAttendance empAttendance = empAttendanceRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Attendance Record not found !"));
		empAttendance.setApprovalStatus(status);

		empAttendanceRepo.save(empAttendance);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Attendance has been marked" + status.toString() + "Successfully !!");
		return respMap;
	}

	public List<EmployeeAttendance> getAttendanceById(Long id) {
		Employees emp = empRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found !"));

		return empAttendanceRepo.findByEmployeeId(emp);

	}

	public List<EmployeeAttendance> getPendingAttendanceListByManagerId(Long id, ApprovalStatus status) {
		empRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found !"));
		return empAttendanceRepo.findAttendanceByManagerId(id, status);

	}
}
