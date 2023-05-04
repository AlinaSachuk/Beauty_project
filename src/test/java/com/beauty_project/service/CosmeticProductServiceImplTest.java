package com.beauty_project.service;

import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.Status;
import com.beauty_project.domain.dto.CreateUpdateStatusDto;
import com.beauty_project.repository.CosmeticProductRepository;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.service.impl.CosmeticProductServiceImpl;
import com.beauty_project.service.impl.StatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class CosmeticProductServiceImplTest {
    @Mock
    private CosmeticProductRepository cosmeticProductRepository;
    @InjectMocks
    private CosmeticProductServiceImpl cosmeticProductService;
    private int id;
    private CosmeticProduct product;
    private ArrayList<CosmeticProduct> allProducts;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(cosmeticProductRepository).findById(id);
        assertThrows(NotFoundException.class, () -> cosmeticProductService.getCosmeticProductById(id));
    }

    @Test
    public void testGetAllStatus() {
        when(cosmeticProductRepository.findAll()).thenReturn(allProducts);
        ArrayList<CosmeticProduct> returnedProducts = cosmeticProductService.getAllCosmeticProducts();
        verify(cosmeticProductRepository).findAll();
        assertEquals(allProducts, returnedProducts);
    }
    @Test
    public void testDeleteStatusByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(cosmeticProductRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> cosmeticProductService.deleteCosmeticProduct(id));
    }
}
