package com.beauty_project.service;

import com.beauty_project.domain.request.CosmeticProductRequestDto;
import com.beauty_project.domain.response.CosmeticProductResponseDto;

import java.util.List;

public interface CosmeticProductService {
    CosmeticProductResponseDto getCosmeticProductById(int id);

    List<CosmeticProductResponseDto> getAllCosmeticProducts();

    CosmeticProductResponseDto createCosmeticProduct(CosmeticProductRequestDto productDto);

    CosmeticProductResponseDto updateCosmeticProduct(CosmeticProductRequestDto productDto);

    void deleteCosmeticProduct(int id);
}