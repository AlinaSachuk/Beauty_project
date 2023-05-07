package com.beauty_project.controller;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.request.CreateUpdateCosmeticProductDto;
import com.beauty_project.service.CosmeticProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class CosmeticProductController {
    private final CosmeticProductService cosmeticProductService;

    @Autowired
    public CosmeticProductController(CosmeticProductService cosmeticProductService) {
        this.cosmeticProductService = cosmeticProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CosmeticProduct> getCosmeticProductById(@PathVariable int id) {
        CosmeticProduct product = cosmeticProductService.getCosmeticProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CosmeticProduct>> getAllCosmeticProducts() {
        List<CosmeticProduct> list = cosmeticProductService.getAllCosmeticProducts();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCosmeticProduct(@RequestBody CreateUpdateCosmeticProductDto productDto) {
        cosmeticProductService.createCosmeticProduct(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateCosmeticProduct(@RequestBody CreateUpdateCosmeticProductDto productDto) {
        cosmeticProductService.updateCosmeticProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCosmeticProduct(@PathVariable int id) {
        cosmeticProductService.deleteCosmeticProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}