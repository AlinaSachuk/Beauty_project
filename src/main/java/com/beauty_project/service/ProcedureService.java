package com.beauty_project.service;

import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.domain.response.AllProceduresResponseDto;
import com.beauty_project.domain.response.ProcedureResponseDto;

import java.util.List;

public interface ProcedureService {

    ProcedureResponseDto getProcedureById(int id);

    List<AllProceduresResponseDto> getAllProcedures();

    ProcedureResponseDto createProcedure(ProcedureRequestDto procedureDto);

    ProcedureResponseDto updateProcedure(ProcedureRequestDto procedureDto);

    void deleteProcedure(int id);
}