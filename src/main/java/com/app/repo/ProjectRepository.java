package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Projects;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
	@Query(nativeQuery = true,
			value = "SELECT p.id, p.project_name, e.first_name FROM ems.projects p left join employees e on e.project_id = p.id  order by p.id desc")
	List<Object> findAllPorjectWithEmployees();
}
