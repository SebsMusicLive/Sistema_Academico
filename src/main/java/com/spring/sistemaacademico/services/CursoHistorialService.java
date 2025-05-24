package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.CursoHistorial;
import java.util.List;

public interface CursoHistorialService extends CrudService<CursoHistorial, Long> {
    List<CursoHistorial> findByCalificacionFinal(float calificacionFinal);
    List<CursoHistorial> findByCodigoEstudiante(Long codigoEstudiante);
}