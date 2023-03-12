package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Positions;
import com.app.entity.Roles;

@Repository
public interface PositionRepository extends JpaRepository<Positions, Long> {
	List<Positions> findByRole(Roles name);
}
