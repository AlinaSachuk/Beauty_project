package com.beauty_project.service;

import com.beauty_project.domain.Procedures;
import com.beauty_project.repository.ProceduresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProceduresService {
    ProceduresRepository proceduresRepository;

    @Autowired
    public ProceduresService(ProceduresRepository proceduresRepository) {
        this.proceduresRepository = proceduresRepository;
    }

    public Optional<Procedures> getProcedureById(int id) {
        return proceduresRepository.findById(id);
    }

    public ArrayList<Procedures> getAllProcedures() {
        return (ArrayList<Procedures>) proceduresRepository.findAll();
    }

    public Procedures createProcedure(Procedures procedure) {
        return proceduresRepository.save(procedure);
    }

    public Procedures updateProcedure(Procedures procedure) {
        return proceduresRepository.saveAndFlush(procedure);
    }

    public void deleteProcedure(int id) {
        proceduresRepository.deleteById(id);
    }
}
