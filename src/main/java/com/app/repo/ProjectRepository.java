package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Projects;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {

}
