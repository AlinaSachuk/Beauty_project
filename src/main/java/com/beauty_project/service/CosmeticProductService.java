package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.dto.CreateUpdateCosmeticProductDto;

import java.util.ArrayList;
import java.util.Optional;

public interface CosmeticProductService {
    Optional<CosmeticProduct> getCosmeticProductById(int id);

    ArrayList<CosmeticProduct> getAllCosmeticProducts();

    CosmeticProduct createCosmeticProduct(CreateUpdateCosmeticProductDto productDto);

    CosmeticProduct updateCosmeticProduct(CreateUpdateCosmeticProductDto productDto);

    void deleteCosmeticProduct(int id);
}