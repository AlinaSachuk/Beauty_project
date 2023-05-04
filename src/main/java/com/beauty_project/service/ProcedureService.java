package com.beauty_project.service;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.dto.CreateUpdateProcedureDto;

import java.util.ArrayList;
import java.util.Optional;

public interface ProcedureService {
    Optional<Procedure> getProcedureById(int id);

    ArrayList<Procedure> getAllProcedures();

    Procedure createProcedure(CreateUpdateProcedureDto procedureDto);

    Procedure updateProcedure(CreateUpdateProcedureDto procedureDto);

    public void deleteProcedure(int id);
}