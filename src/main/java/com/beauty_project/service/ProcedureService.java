package com.beauty_project.service;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.request.ProcedureRequestDto;

import java.util.List;

public interface ProcedureService {
    Procedure getProcedureById(int id);

    List<Procedure> getAllProcedures();

    Procedure createProcedure(ProcedureRequestDto procedureDto);

    Procedure updateProcedure(ProcedureRequestDto procedureDto);

    void deleteProcedure(int id);
}