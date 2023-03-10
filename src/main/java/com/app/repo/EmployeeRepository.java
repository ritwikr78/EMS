package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Departments;
import com.app.entity.Employees;
import com.app.entity.Positions;
import com.app.entity.Projects;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
	// Finder Method
	Employees findByEmailAndPassword(String email, String Password);

	Employees findByEmail(String email);

	List<Employees> findByEmployeeManagerId(Employees empManager);

	@Query(value = "select * from employees where id = (select manager_id from employees where id =:id )", nativeQuery = true)
	Employees getManagerDetailsById(@Param("id") Long id);

	List<Employees> findByPosition(Positions pos);

	List<Employees> findByProject(Projects project);

	List<Employees> findByDepartment(Departments Dept);
}
