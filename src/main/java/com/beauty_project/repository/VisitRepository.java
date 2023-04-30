package com.beauty_project.repository;

import com.beauty_project.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    @Query(nativeQuery = true, countQuery = "SELECT (date_of_visit, final_price) FROM visit_table e WHERE e.customer_id=:id")
    List<Visit> findVisitsByCustomerId(int id);
}