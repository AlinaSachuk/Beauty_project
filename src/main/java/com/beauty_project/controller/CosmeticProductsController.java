package com.beauty_project.controller;

import com.beauty_project.domain.CosmeticProducts;
import com.beauty_project.service.CosmeticProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class CosmeticProductsController {
    CosmeticProductsService cosmeticProductsService;

    @Autowired
    public CosmeticProductsController(CosmeticProductsService cosmeticProductsService) {
        this.cosmeticProductsService = cosmeticProductsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CosmeticProducts>> getCosmeticProductById(@PathVariable int id) {
        Optional<CosmeticProducts> product = cosmeticProductsService.getCosmeticProductById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AbstractList<CosmeticProducts>> getAllCosmeticProducts() {
        ArrayList<CosmeticProducts> list = cosmeticProductsService.getAllCosmeticProducts();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCosmeticProduct(@RequestBody CosmeticProducts product) {
        cosmeticProductsService.createCosmeticProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateCosmeticProduct(@RequestBody CosmeticProducts product) {
        cosmeticProductsService.updateCosmeticProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCosmeticProduct(@PathVariable int id) {
        cosmeticProductsService.deleteCosmeticProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
