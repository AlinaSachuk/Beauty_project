package com.beauty_project.repository;

import com.beauty_project.domain.CosmeticProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosmeticProductRepository extends JpaRepository<CosmeticProduct, Integer> {
}