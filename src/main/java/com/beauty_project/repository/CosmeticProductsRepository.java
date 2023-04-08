package com.beauty_project.repository;

import com.beauty_project.domain.CosmeticProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosmeticProductsRepository extends JpaRepository<CosmeticProducts, Integer> {
}
