package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProduct;

import java.util.ArrayList;
import java.util.Optional;

public interface CosmeticProductService {
    Optional<CosmeticProduct> getCosmeticProductById(int id);

    ArrayList<CosmeticProduct> getAllCosmeticProducts();

    CosmeticProduct createCosmeticProduct(CosmeticProduct product);

    CosmeticProduct updateCosmeticProduct(CosmeticProduct product);

    void deleteCosmeticProduct(int id);
}