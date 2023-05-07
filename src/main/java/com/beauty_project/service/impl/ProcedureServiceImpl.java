package com.beauty_project.service.impl;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public Procedure getProcedureById(int id) {
        return procedureRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Procedure by id=" + id + " not found"));
    }

    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    public Procedure createProcedure(ProcedureRequestDto procedureDto) {
        Procedure procedure = new Procedure();
        procedure.setServiceName(procedureDto.getServiceName());
        procedure.setDuration(procedureDto.getDuration());
        procedure.setPrice(procedureDto.getPrice());
        procedure.setDescription(procedureDto.getDescription());
        return procedureRepository.save(procedure);
    }

    public Procedure updateProcedure(ProcedureRequestDto procedureDto) {
        Procedure procedure = procedureRepository.findById(procedureDto.getId())
                .orElseThrow(()-> new NotFoundException("Procedure by id=" + procedureDto.getId() + " not found"));
        procedure.setServiceName(procedureDto.getServiceName());
        procedure.setDuration(procedureDto.getDuration());
        procedure.setPrice(procedureDto.getPrice());
        procedure.setDescription(procedureDto.getDescription());
        return procedureRepository.saveAndFlush(procedure);
    }

    public void deleteProcedure(int id) {
        procedureRepository.deleteById(id);
    }
}