package com.beauty_project.service.impl;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.domain.response.ProcedureResponseDto;
import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public ProcedureResponseDto getProcedureById(int id) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Procedure by id=" + id + " not found"));
        return new ProcedureResponseDto(
                procedure.getId(),
                procedure.getServiceName(),
                procedure.getDuration(),
                procedure.getPrice(),
                procedure.getDescription()
        );
    }

    @Override
    public List<ProcedureResponseDto> getAllProcedures() {
        List<Procedure> procedures = procedureRepository.findAll();
        return procedures.stream().map(procedure -> new ProcedureResponseDto(
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
        Procedure savedProcedure = procedureRepository.save(procedure);
        return new ProcedureResponseDto(
                savedProcedure.getId(),
                savedProcedure.getServiceName(),
                savedProcedure.getDuration(),
                savedProcedure.getPrice(),
                savedProcedure.getDescription()
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
        Procedure savedProcedure = procedureRepository.save(procedure);
        return new ProcedureResponseDto(
                savedProcedure.getId(),
                savedProcedure.getServiceName(),
                savedProcedure.getDuration(),
                savedProcedure.getPrice(),
                savedProcedure.getDescription()
        );
    }

    @Override
    public void deleteProcedure(int id) {
        procedureRepository.deleteById(id);
    }
}