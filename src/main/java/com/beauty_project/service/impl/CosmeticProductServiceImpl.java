package com.beauty_project.service.impl;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.dto.CreateUpdateCosmeticProductDto;
import com.beauty_project.repository.CosmeticProductRepository;
import com.beauty_project.service.CosmeticProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CosmeticProductServiceImpl implements CosmeticProductService {
    private final CosmeticProductRepository cosmeticProductRepository;

    @Autowired
    public CosmeticProductServiceImpl(CosmeticProductRepository cosmeticProductRepository) {
        this.cosmeticProductRepository = cosmeticProductRepository;
    }

    public Optional<CosmeticProduct> getCosmeticProductById(int id) {
        return cosmeticProductRepository.findById(id);
    }

    public ArrayList<CosmeticProduct> getAllCosmeticProducts() {
        ArrayList<CosmeticProduct> list = (ArrayList<CosmeticProduct>) cosmeticProductRepository.findAll();
        return list;
    }

    public CosmeticProduct createCosmeticProduct(CreateUpdateCosmeticProductDto productDto) {
        CosmeticProduct product = new CosmeticProduct();
        product.setProductName(productDto.getProductName());
        product.setManufacture(productDto.getManufacture());
        product.setCountryOfOrigin(productDto.getCountryOfOrigin());
        return cosmeticProductRepository.save(product);
    }

    public CosmeticProduct updateCosmeticProduct(CreateUpdateCosmeticProductDto productDto) {
        CosmeticProduct product = cosmeticProductRepository.findById(productDto.getId())
                .orElseThrow(()-> new NotFoundException("Product by id=" + productDto.getId() + " not found"));
        product.setProductName(productDto.getProductName());
        product.setManufacture(productDto.getManufacture());
        product.setCountryOfOrigin(productDto.getCountryOfOrigin());
        return cosmeticProductRepository.save(product);
    }

    public void deleteCosmeticProduct(int id) {
        cosmeticProductRepository.deleteById(id);
    }
}