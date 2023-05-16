package com.beauty_project.service.impl;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.domain.response.AllProceduresResponseDto;
import com.beauty_project.domain.response.CosmeticProductResponseDto;
import com.beauty_project.domain.response.ProcedureResponseDto;
import com.beauty_project.repository.CosmeticProductRepository;
import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;
    private final CosmeticProductRepository cosmeticProductRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository
            , CosmeticProductRepository cosmeticProductRepository) {
        this.procedureRepository = procedureRepository;
        this.cosmeticProductRepository = cosmeticProductRepository;
    }

    @Override
    public ProcedureResponseDto getProcedureById(int id) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Procedure by id=" + id + " not found"));
        Set<CosmeticProductResponseDto> cosmeticProductResponse = procedure.getProductList()
                .stream().map(cosmeticProduct -> new CosmeticProductResponseDto(
                        cosmeticProduct.getId(),
                        cosmeticProduct.getProductName(),
                        cosmeticProduct.getManufacture(),
                        cosmeticProduct.getCountryOfOrigin()
                )).collect(Collectors.toSet());
        return new ProcedureResponseDto(
                procedure.getId(),
                procedure.getServiceName(),
                procedure.getDuration(),
                procedure.getPrice(),
                procedure.getDescription(),
                cosmeticProductResponse
        );
    }

    @Override
    public List<AllProceduresResponseDto> getAllProcedures() {
        List<Procedure> procedures = procedureRepository.findAll();
        return procedures.stream().map(procedure -> new AllProceduresResponseDto(
                procedure.getId(),
                procedure.getServiceName(),
                procedure.getDuration(),
                procedure.getPrice(),
                procedure.getDescription()
        )).collect(Collectors.toList());
    }

    @Override
    public ProcedureResponseDto createProcedure(ProcedureRequestDto procedureDto) {
        Procedure procedure = new Procedure();
        procedure.setServiceName(procedureDto.getServiceName());
        procedure.setDuration(procedureDto.getDuration());
        procedure.setPrice(procedureDto.getPrice());
        procedure.setDescription(procedureDto.getDescription());
        procedure.setProductList(new HashSet<>(cosmeticProductRepository
                .findAllById(procedureDto.getCosmeticProductIds())));
        Procedure savedProcedure = procedureRepository.save(procedure);
        Set<CosmeticProductResponseDto> cosmeticProductResponse = savedProcedure.getProductList()
                .stream().map(cosmeticProduct -> new CosmeticProductResponseDto(
                        cosmeticProduct.getId(),
                        cosmeticProduct.getProductName(),
                        cosmeticProduct.getManufacture(),
                        cosmeticProduct.getCountryOfOrigin()
                )).collect(Collectors.toSet());
        return new ProcedureResponseDto(
                savedProcedure.getId(),
                savedProcedure.getServiceName(),
                savedProcedure.getDuration(),
                savedProcedure.getPrice(),
                savedProcedure.getDescription(),
                cosmeticProductResponse
        );
    }

    @Override
    public ProcedureResponseDto updateProcedure(ProcedureRequestDto procedureDto) {
        Procedure procedure = procedureRepository.findById(procedureDto.getId())
                .orElseThrow(() -> new NotFoundException("Procedure by id=" + procedureDto.getId() + " not found"));
        procedure.setServiceName(procedureDto.getServiceName());
        procedure.setDuration(procedureDto.getDuration());
        procedure.setPrice(procedureDto.getPrice());
        procedure.setDescription(procedureDto.getDescription());
        Procedure updatedProcedure = procedureRepository.save(procedure);
        Set<CosmeticProductResponseDto> cosmeticProductResponse = updatedProcedure.getProductList()
                .stream().map(cosmeticProduct -> new CosmeticProductResponseDto(
                        cosmeticProduct.getId(),
                        cosmeticProduct.getProductName(),
                        cosmeticProduct.getManufacture(),
                        cosmeticProduct.getCountryOfOrigin()
                )).collect(Collectors.toSet());
        return new ProcedureResponseDto(
                updatedProcedure.getId(),
                updatedProcedure.getServiceName(),
                updatedProcedure.getDuration(),
                updatedProcedure.getPrice(),
                updatedProcedure.getDescription(),
                cosmeticProductResponse
        );
    }

    @Override
    public void deleteProcedure(int id) {
        procedureRepository.deleteById(id);
    }
}