package com.beauty_project.controller;

import com.beauty_project.domain.request.CosmeticProductRequestDto;
import com.beauty_project.domain.response.CosmeticProductResponseDto;
import com.beauty_project.service.CosmeticProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Don`t have authorization"),
        @ApiResponse(responseCode = "403", description = "Don`t have authority")
})
@RequestMapping("/product")
public class CosmeticProductController {
    private final CosmeticProductService cosmeticProductService;

    @Autowired
    public CosmeticProductController(CosmeticProductService cosmeticProductService) {
        this.cosmeticProductService = cosmeticProductService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Finding cosmetic product by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cosmetic product found by id successfully."),
            @ApiResponse(responseCode = "404", description = "Cosmetic product by entered id not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public CosmeticProductResponseDto getCosmeticProductById(@PathVariable int id) {
        return cosmeticProductService.getCosmeticProductById(id);
    }

    @GetMapping
    @Operation(description = "Finding all cosmetic products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All cosmetic products found successfully"),
            @ApiResponse(responseCode = "404", description = "Cosmetic products not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<CosmeticProductResponseDto> getAllCosmeticProducts() {
        return cosmeticProductService.getAllCosmeticProducts();
    }

    @PostMapping
    @Operation(description = "Creating cosmetic product.")
    @ApiResponse(responseCode = "201", description = "Cosmetic product created successfully.")
    @ResponseStatus(HttpStatus.CREATED)
    public CosmeticProductResponseDto createCosmeticProduct(@RequestBody CosmeticProductRequestDto productDto) {
        return cosmeticProductService.createCosmeticProduct(productDto);
    }

    @PutMapping
    @Operation(description = "Updating cosmetic product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cosmetic product updated successfully."),
            @ApiResponse(responseCode = "404", description = "Requested cosmetic product not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public CosmeticProductResponseDto updateCosmeticProduct(@RequestBody CosmeticProductRequestDto productDto) {
        return cosmeticProductService.updateCosmeticProduct(productDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleting cosmetic product by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cosmetic product deleted by id successfully."),
            @ApiResponse(responseCode = "404", description = "Requested cosmetic product not found.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCosmeticProduct(@PathVariable int id) {
        cosmeticProductService.deleteCosmeticProduct(id);
    }
}