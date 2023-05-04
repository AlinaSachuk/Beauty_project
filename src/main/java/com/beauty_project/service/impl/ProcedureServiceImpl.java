package com.beauty_project.service.impl;

import com.beauty_project.domain.Procedure;
import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public Optional<Procedure> getProcedureById(int id) {
        return procedureRepository.findById(id);
    }

    public ArrayList<Procedure> getAllProcedures() {
        return (ArrayList<Procedure>) procedureRepository.findAll();
    }

    public Procedure createProcedure(Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    public Procedure updateProcedure(Procedure procedure) {
        return procedureRepository.saveAndFlush(procedure);
    }

    public void deleteProcedure(int id) {
        procedureRepository.deleteById(id);
    }
}