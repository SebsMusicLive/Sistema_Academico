package com.spring.sistemaacademico.services;


import com.spring.sistemaacademico.model.Calificacion;

import java.util.List;

public interface CalificacionService extends CrudService<Calificacion, Long> {

    List<Calificacion> findByEstudianteId(Long estudianteId);
    List<Calificacion> findByNota(float nota);
}
