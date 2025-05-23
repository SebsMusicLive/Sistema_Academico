package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.CursoHistorial;

import java.util.List;

@Service
public interface CursoHistorialService extends CrudService<CursoHistorial, Long> {

    public List<CursoHistorial> findByCalificacionFinal(float calificacionFinal);
}
