package com.spring.sistemaacademico.services;


import com.spring.sistemaacademico.model.Calificacion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CalificacionService extends CrudService<Calificacion, Long> {

    List<Calificacion> findByEstudianteCodigoEstudiante(Long codigoEstudiante);
    List<Calificacion> findByNota(float nota);
}
