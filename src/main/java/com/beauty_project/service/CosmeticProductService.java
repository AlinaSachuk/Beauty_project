package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.request.CreateUpdateCosmeticProductDto;

import java.util.List;

public interface CosmeticProductService {
    CosmeticProduct getCosmeticProductById(int id);

    List<CosmeticProduct> getAllCosmeticProducts();

    CosmeticProduct createCosmeticProduct(CreateUpdateCosmeticProductDto productDto);

    CosmeticProduct updateCosmeticProduct(CreateUpdateCosmeticProductDto productDto);

    void deleteCosmeticProduct(int id);
}