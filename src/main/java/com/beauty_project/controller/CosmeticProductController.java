package com.beauty_project.controller;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.service.CosmeticProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class CosmeticProductController {
    CosmeticProductService cosmeticProductService;

    @Autowired
    public CosmeticProductController(CosmeticProductService cosmeticProductService) {
        this.cosmeticProductService = cosmeticProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CosmeticProduct>> getCosmeticProductById(@PathVariable int id) {
        Optional<CosmeticProduct> product = cosmeticProductService.getCosmeticProductById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AbstractList<CosmeticProduct>> getAllCosmeticProducts() {
        ArrayList<CosmeticProduct> list = cosmeticProductService.getAllCosmeticProducts();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCosmeticProduct(@RequestBody CosmeticProduct product) {
        cosmeticProductService.createCosmeticProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateCosmeticProduct(@RequestBody CosmeticProduct product) {
        cosmeticProductService.updateCosmeticProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCosmeticProduct(@PathVariable int id) {
        cosmeticProductService.deleteCosmeticProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
