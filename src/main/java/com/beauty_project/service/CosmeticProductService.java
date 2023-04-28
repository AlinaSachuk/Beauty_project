package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.repository.CosmeticProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CosmeticProductService {
    CosmeticProductRepository cosmeticProductRepository;

    @Autowired
    public CosmeticProductService(CosmeticProductRepository cosmeticProductRepository) {
        this.cosmeticProductRepository = cosmeticProductRepository;
    }

    public Optional<CosmeticProduct> getCosmeticProductById(int id) {
        return cosmeticProductRepository.findById(id);
    }

    public ArrayList<CosmeticProduct> getAllCosmeticProducts() {
        ArrayList<CosmeticProduct> list = (ArrayList<CosmeticProduct>) cosmeticProductRepository.findAll();
        return list;
    }

    public CosmeticProduct createCosmeticProduct(CosmeticProduct product) {
        return cosmeticProductRepository.save(product);
    }

    public CosmeticProduct updateCosmeticProduct(CosmeticProduct product) {
        return cosmeticProductRepository.save(product);
    }

    public void deleteCosmeticProduct(int id) {
        cosmeticProductRepository.deleteById(id);
    }
}