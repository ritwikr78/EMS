package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.ApprovalStatus;
import com.app.entity.EmployeeAttendance;
import com.app.entity.Employees;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

	public List<EmployeeAttendance> findByApprovalStatus(ApprovalStatus status);

	public List<EmployeeAttendance> findByEmployeeId(Employees emp);

	@Query(value = "Select emp.first_name, emp.last_name, emp.id, ea.date, ea.in_time, ea.out_time from employees emp join employee_attendance ea on emp.id = ea.employee_id where emp.manager_id =:id and ea.approval_status =:status", nativeQuery = true)
	public List<EmployeeAttendance> findAttendanceByManagerId(@Param("id") long id,
			@Param("status") ApprovalStatus status);
}
