package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Positions;

@Repository
public interface PositionRepository extends JpaRepository<Positions, Long> {

}
