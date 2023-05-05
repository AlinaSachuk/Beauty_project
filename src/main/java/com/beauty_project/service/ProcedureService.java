package com.beauty_project.service;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.dto.CreateUpdateProcedureDto;

import java.util.List;

public interface ProcedureService {
    Procedure getProcedureById(int id);

    List<Procedure> getAllProcedures();

    Procedure createProcedure(CreateUpdateProcedureDto procedureDto);

    Procedure updateProcedure(CreateUpdateProcedureDto procedureDto);

    void deleteProcedure(int id);
}