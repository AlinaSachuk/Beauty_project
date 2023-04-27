package com.beauty_project.repository;

import com.beauty_project.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query(nativeQuery = true, value = "SELECT percent from status_table u WHERE u.status=:status")
    Integer findPercentByStatus(@Param("status") String status);
}
