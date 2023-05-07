package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.request.CosmeticProductRequestDto;

import java.util.List;

public interface CosmeticProductService {
    CosmeticProduct getCosmeticProductById(int id);

    List<CosmeticProduct> getAllCosmeticProducts();

    CosmeticProduct createCosmeticProduct(CosmeticProductRequestDto productDto);

    CosmeticProduct updateCosmeticProduct(CosmeticProductRequestDto productDto);

    void deleteCosmeticProduct(int id);
}