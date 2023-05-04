package com.beauty_project.service;

import com.beauty_project.domain.Procedure;

import java.util.ArrayList;
import java.util.Optional;

public interface ProcedureService {
    Optional<Procedure> getProcedureById(int id);

    ArrayList<Procedure> getAllProcedures();

    Procedure createProcedure(Procedure procedure);

    Procedure updateProcedure(Procedure procedure);

    public void deleteProcedure(int id);
}