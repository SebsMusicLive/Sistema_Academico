package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.CursoHistorial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CursoHistorialService extends CrudService<CursoHistorial, Long> {

    public List<CursoHistorial> findByCalificacionFinal(float calificacionFinal);
}
