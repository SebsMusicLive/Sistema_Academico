package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.CursoHistorial;
import com.spring.sistemaacademico.model.HistorialAcademico;

import java.util.List;

public interface HistorialAcademicoService extends CrudService<HistorialAcademico, Long> {

    List<HistorialAcademico> findByPromedioGeneral(float promedioGeneral);

    String generarReporteDesempeno(Long idHistorial) throws Exception;

    float calcularProyeccionRendimiento(Long idHistorial) throws Exception;

    List<CursoHistorial> generarReporteCursosAprobados(Long idHistorial) throws Exception;

    List<CursoHistorial> generarReporteCursosEnProceso(Long idHistorial) throws Exception;

    HistorialAcademico agregarCursoHistorial(Long idHistorial, CursoHistorial nuevoCurso) throws Exception;

    List<CursoHistorial> consultarHistorial(Long idHistorial) throws Exception;
}
