package com.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.EmployeeDataInsertion;
import com.app.dto.LoginRequest;
import com.app.entity.Departments;
import com.app.entity.Employees;
import com.app.entity.Positions;
import com.app.entity.Projects;
import com.app.repo.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private PositionService positionService;

	private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();

	// method to get all employees
	public List<Employees> getAllEmployeesDetails() {
		return employeeRepo.findAll();
	}

	// method to get employee by id
	public Employees getEmployeesDetailsById(Long id) {
		return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
	}

	// method to add new employees
	public Map<String, String> addEmployeeDetails(EmployeeDataInsertion emp) {
		Departments departmentsById = departmentService.getDepartmentsById(emp.getDepartment_id());
		Positions positionById = positionService.getPositionsById(emp.getPosition_id());

		Employees employee = new Employees();
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setPassword(BCRYPT.encode("password@123"));
		employee.setEmail(emp.getEmail());
		employee.setDob(LocalDate.parse(emp.getDob()));
		employee.setDateOfJoining(LocalDate.parse(emp.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		employee.setDepartment(departmentsById);
		employee.setPosition(positionById);
		employee.setAddress(emp.getAddress());
		employee.setGender(emp.getGender());
		employee.setIsActive(true);
		employee.setSalary(emp.getSalary());
		employee.setPhoneNumber(emp.getPhoneNumber());
		employee.setQualification(emp.getQualification());

		employeeRepo.save(employee);
		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Employee Added Successfully");
		return respMap;
	}

	// method to update employee details
	public Map<String, String> updateEmployeeDetails(Employees emp) {
		Employees persistEmpDetails = this.getEmployeesDetailsById(emp.getId());

		persistEmpDetails.setFirstName(emp.getFirstName());
		persistEmpDetails.setLastName(emp.getLastName());
		persistEmpDetails.setEmail(emp.getEmail());
		persistEmpDetails.setPhoneNumber(emp.getPhoneNumber());
		persistEmpDetails.setDepartment(emp.getDepartment());
		persistEmpDetails.setPosition(emp.getPosition());
		persistEmpDetails.setSalary(emp.getSalary());
		persistEmpDetails.setQualification(emp.getQualification());
		persistEmpDetails.setDob(emp.getDob());
		persistEmpDetails.setDateOfJoining(emp.getDateOfJoining());

		employeeRepo.save(persistEmpDetails);
		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Employee Updated Successfully");
		return respMap;

	}

	// method to authenticate employee
	public Employees authenticateEmployee(LoginRequest login) {
		Employees emp = employeeRepo.findByEmail(login.getEmail());

		if (BCRYPT.matches(login.getPassword(), emp.getPassword()))
			return emp;

		return null;

	}

	// method to find employee by email
	public Employees findEmployeeByEmail(String email) {
		return employeeRepo.findByEmail(email);
	}

	// Key : Emp Id, Value : Manager Id
	// method to assign manager to an employee
	public Map<String, String> addEmployeeManager(HashMap<String, Long> empManagerMap) {
		Long empId = empManagerMap.get("empId");
		Long mgrId = empManagerMap.get("mgrId");

		Employees emp = getEmployeesDetailsById(empId);
		Employees mgr = getEmployeesDetailsById(mgrId);

		emp.setEmployeeManagerId(mgr);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Manager added to employee successfully");
		return respMap;

	}

	// Key : Emp Id, Value : Project Id
	// method to assign Project to an employee
	public Map<String, String> addEmployeeProject(HashMap<String, Long> empProjectMap) {
		Long empId = empProjectMap.get("empId");
		Long projectId = empProjectMap.get("projectId");

		Employees emp = getEmployeesDetailsById(empId);
		Projects project = projectService.getProjectDetailsById(projectId);

		emp.setProject(project);
		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Project added to employee successfully");
		return respMap;

	}

	// method to mark left employees
	public Map<String, String> leftEmployeeUpdateRecord(Long emp_id) {
		Employees emp = getEmployeesDetailsById(emp_id);

		List<Employees> findByEmployeeManagerId = employeeRepo.findByEmployeeManagerId(emp);
		emp.setEmployeeManagerId(null);
		emp.setIsActive(false);

		findByEmployeeManagerId.forEach(e -> e.setEmployeeManagerId(null));

		employeeRepo.saveAll(findByEmployeeManagerId);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Employee has been marked as left");
		return respMap;
	}

	// method to get employee manager details
	public Map<String, String> getManagerEmployeeDetails(Long emp_id) {
		Employees managerDetailsById = employeeRepo.getManagerDetailsById(emp_id);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("firstName", managerDetailsById.getFirstName());
		respMap.put("lastName", managerDetailsById.getLastName());
		respMap.put("email", managerDetailsById.getEmail());
		respMap.put("phoneNumber", managerDetailsById.getPhoneNumber());
		return respMap;
	}

	// method to change password
	public Map<String, String> changePassword(HashMap<String, String> mapEmailPassword) {

		String email = mapEmailPassword.get("email");
		Employees findByEmail = null;

		if (email != null)
			findByEmail = employeeRepo.findByEmail(email);

		String password = mapEmailPassword.get("password");
		if (password != null)

			// change password
			findByEmail.setPassword(BCRYPT.encode(password));
		employeeRepo.save(findByEmail);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Employee Password has been Changed !");
		return respMap;

	}

}