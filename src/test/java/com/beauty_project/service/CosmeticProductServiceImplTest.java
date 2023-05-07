package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.repository.CosmeticProductRepository;
import com.beauty_project.service.impl.CosmeticProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CosmeticProductServiceImplTest {
    @Mock
    private CosmeticProductRepository cosmeticProductRepository;
    @InjectMocks
    private CosmeticProductServiceImpl cosmeticProductService;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(cosmeticProductRepository).findById(1);
        assertThrows(NotFoundException.class, () -> cosmeticProductService.getCosmeticProductById(1));
    }

    @Test
    public void testGetAllCosmeticProducts() {
        CosmeticProduct product1 = Utils.createCosmeticProduct(1, "Cream", "Nivea", "Sweden");
        CosmeticProduct product2 = Utils.createCosmeticProduct(2, "Cream", "Nivea", "Sweden");
        List<CosmeticProduct> returnedProducts = new ArrayList<>();
        returnedProducts.add(product1);
        returnedProducts.add(product2);
        when(cosmeticProductRepository.findAll()).thenReturn(returnedProducts);
        List<CosmeticProduct> actual = cosmeticProductRepository.findAll();
        verify(cosmeticProductRepository, times(1)).findAll();
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    /*@Test
    public void testSave() {
        setDataForSaveAndUpdate();
        CosmeticProduct actual = cosmeticProductService.createCosmeticProduct(
                new CreateUpdateCosmeticProductDto(1, "Cream", "Nivea", "Sweden"));
        assertNotNull(actual);
        assertEquals(new CreateUpdateCosmeticProductDto(1, "Cream", "Nivea", "Sweden"), actual);
    }

    @Test
    public void testUpdate() {
        CosmeticProduct actual = cosmeticProductService.updateCosmeticProduct(
                new CreateUpdateCosmeticProductDto(1,"Cream", "Nivea", "Sweden"));
        assertNotNull(actual);
        assertEquals(new CreateUpdateCosmeticProductDto(1, "Cream", "Nivea", "Sweden"), actual);
    }*/

    @Test
    public void testDeleteCosmeticProductById() {
        cosmeticProductService.deleteCosmeticProduct(1);
        verify(cosmeticProductRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCosmeticProductByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(cosmeticProductRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> cosmeticProductService.deleteCosmeticProduct(1));
    }

    private void setDataForSaveAndUpdate() {
        var product = Utils.createCosmeticProduct(1, "Cream", "Nivea", "Sweden");
        when(cosmeticProductRepository.save(any())).thenReturn(product);
    }
}