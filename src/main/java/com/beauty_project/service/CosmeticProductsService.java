package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProducts;
import com.beauty_project.repository.CosmeticProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CosmeticProductsService {
    CosmeticProductsRepository cosmeticProductsRepository;

    @Autowired
    public CosmeticProductsService(CosmeticProductsRepository cosmeticProductsRepository) {
        this.cosmeticProductsRepository = cosmeticProductsRepository;
    }

    public Optional<CosmeticProducts> getCosmeticProductById(int id) {
        return cosmeticProductsRepository.findById(id);
    }

    public ArrayList<CosmeticProducts> getAllCosmeticProducts() {
        ArrayList<CosmeticProducts> list = (ArrayList<CosmeticProducts>) cosmeticProductsRepository.findAll();
        return list;
    }

    public CosmeticProducts createCosmeticProduct(CosmeticProducts product) {
        return cosmeticProductsRepository.save(product);
    }

    public CosmeticProducts updateCosmeticProduct(CosmeticProducts product) {
        return cosmeticProductsRepository.save(product);
    }

    public void deleteCosmeticProduct(int id) {
        cosmeticProductsRepository.deleteById(id);
    }
}
