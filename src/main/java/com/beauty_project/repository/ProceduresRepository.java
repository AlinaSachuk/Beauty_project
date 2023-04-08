package com.beauty_project.repository;

import com.beauty_project.domain.Procedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProceduresRepository extends JpaRepository<Procedures, Integer> {
}
