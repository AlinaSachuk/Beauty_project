package com.beauty_project.service;

import com.beauty_project.domain.Procedures;
import com.beauty_project.repository.ProceduresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProceduresService {
    ProceduresRepository proceduresRepository;

    @Autowired
    public ProceduresService(ProceduresRepository proceduresRepository) {
        this.proceduresRepository = proceduresRepository;
    }

    public Procedures getProcedureById(int id) {
        return proceduresRepository.getProcedureById(id);
    }

    public ArrayList<Procedures> getAllProcedures() {
        return proceduresRepository.getAllProcedures();
    }
}
