package com.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Employees;
import com.app.entity.Projects;
import com.app.repo.EmployeeRepository;
import com.app.repo.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	public Projects getProjectDetailsById(Long id) {
		return projectRepo.findById(id).orElseThrow(() -> new RuntimeException("No project by id : " + id + "found"));
	}
	
	public List<Employees> getEmployeesByProjectId(Long id) {
		Projects project = projectRepo.findById(id).get();
		return employeeRepo.findByProject(project);
	}

	public Map<String, String> addNewProject(Projects project, long id) {
		projectRepo.save(project);
		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Project Added & Assigned Successfully");
		return respMap;
	}

	public List<Projects> getAllprojects() {
		return projectRepo.findAll();
	}
	public Object getAllprojectsByUsers() {
		Object obj = projectRepo.findAllPorjectWithEmployees();
		return projectRepo.findAllPorjectWithEmployees();
	}

	public Map<String, String> updateProject(Projects Project) {
		Projects persistantProject = projectRepo.findById(Project.getId())
				.orElseThrow(() -> new RuntimeException("Project Not Found"));

		persistantProject.setProjectName(Project.getProjectName());
		projectRepo.save(persistantProject);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put("message", "Project Updated Successfully");
		return respMap;

	}

	public Map<String, String> deleteProject(Long id) {
		Projects ProjectsById = this.getProjectDetailsById(id);

		List<Employees> findByProject = employeeRepo.findByProject(ProjectsById);

		Map<String, String> respMap = new HashMap<String, String>();
		if (findByProject == null) {
			projectRepo.delete(ProjectsById);
		} else {
			findByProject.forEach(e -> e.setProject(null));
			employeeRepo.saveAll(findByProject);
		}
		respMap.put("message", "Project Deleted Successfully !");
		return respMap;

	}
}
