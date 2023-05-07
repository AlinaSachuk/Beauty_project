package com.beauty_project.service.impl;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.request.CosmeticProductRequestDto;
import com.beauty_project.domain.response.CosmeticProductResponseDto;
import com.beauty_project.repository.CosmeticProductRepository;
import com.beauty_project.service.CosmeticProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CosmeticProductServiceImpl implements CosmeticProductService {
    private final CosmeticProductRepository cosmeticProductRepository;

    @Autowired
    public CosmeticProductServiceImpl(CosmeticProductRepository cosmeticProductRepository) {
        this.cosmeticProductRepository = cosmeticProductRepository;
    }

    @Override
    public CosmeticProductResponseDto getCosmeticProductById(int id) {
        CosmeticProduct product = cosmeticProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product by id=" + id + " not found"));
        return new CosmeticProductResponseDto(
                product.getId(),
                product.getProductName(),
                product.getManufacture(),
                product.getCountryOfOrigin()
        );
    }

    @Override
    public List<CosmeticProductResponseDto> getAllCosmeticProducts() {
        List<CosmeticProduct> products = cosmeticProductRepository.findAll();
        return products.stream().map(cosmeticProduct -> new CosmeticProductResponseDto(
                cosmeticProduct.getId(),
                cosmeticProduct.getProductName(),
                cosmeticProduct.getManufacture(),
                cosmeticProduct.getCountryOfOrigin()
        )).collect(Collectors.toList());
    }

    @Override
    public CosmeticProductResponseDto createCosmeticProduct(CosmeticProductRequestDto productDto) {
        CosmeticProduct product = new CosmeticProduct();
        product.setProductName(productDto.getProductName());
        product.setManufacture(productDto.getManufacture());
        product.setCountryOfOrigin(productDto.getCountryOfOrigin());
        CosmeticProduct savedProduct = cosmeticProductRepository.save(product);
        return new CosmeticProductResponseDto(
                savedProduct.getId(),
                savedProduct.getProductName(),
                savedProduct.getManufacture(),
                savedProduct.getCountryOfOrigin()
        );
    }

    @Override
    public CosmeticProductResponseDto updateCosmeticProduct(CosmeticProductRequestDto productDto) {
        CosmeticProduct product = cosmeticProductRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("Product by id=" + productDto.getId() + " not found"));
        product.setProductName(productDto.getProductName());
        product.setManufacture(productDto.getManufacture());
        product.setCountryOfOrigin(productDto.getCountryOfOrigin());
        CosmeticProduct savedProduct = cosmeticProductRepository.save(product);
        return new CosmeticProductResponseDto(
                savedProduct.getId(),
                savedProduct.getProductName(),
                savedProduct.getManufacture(),
                savedProduct.getCountryOfOrigin()
        );
    }

    @Override
    public void deleteCosmeticProduct(int id) {
        cosmeticProductRepository.deleteById(id);
    }
}