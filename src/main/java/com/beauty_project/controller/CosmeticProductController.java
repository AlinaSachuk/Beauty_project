package com.beauty_project.controller;

import com.beauty_project.domain.request.CosmeticProductRequestDto;
import com.beauty_project.domain.response.CosmeticProductResponseDto;
import com.beauty_project.service.CosmeticProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public CosmeticProductResponseDto getCosmeticProductById(@PathVariable int id) {
        return cosmeticProductService.getCosmeticProductById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CosmeticProductResponseDto> getAllCosmeticProducts() {
        return cosmeticProductService.getAllCosmeticProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CosmeticProductResponseDto createCosmeticProduct(@RequestBody CosmeticProductRequestDto productDto) {
        return cosmeticProductService.createCosmeticProduct(productDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CosmeticProductResponseDto updateCosmeticProduct(@RequestBody CosmeticProductRequestDto productDto) {
        return cosmeticProductService.updateCosmeticProduct(productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCosmeticProduct(@PathVariable int id) {
        cosmeticProductService.deleteCosmeticProduct(id);
    }
}