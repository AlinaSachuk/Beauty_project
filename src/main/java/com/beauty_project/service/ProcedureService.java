package com.beauty_project.service;

import com.beauty_project.domain.Procedure;
import com.beauty_project.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProcedureService {
    ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository) {
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
        procedureRepository.findById(procedure.getId()).orElseThrow(() -> new NotFoundException("Procedure with id=" + procedure.getId() + " not found: updateProcedure method."));
        return procedureRepository.saveAndFlush(procedure);
    }

    public void deleteProcedure(int id) {
        procedureRepository.findById(id).orElseThrow(() -> new NotFoundException("Procedure with id=" + id + " not found: deleteProcedure method."));
        procedureRepository.deleteById(id);
    }
}