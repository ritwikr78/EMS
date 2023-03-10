package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Departments;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {

}
