package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Evaluacion;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EvaluacionService extends CrudService<Evaluacion, Long> {
    List<Evaluacion> findByTipo(String tipo);
    List<Evaluacion> findByPonderacion(float ponderacion);
    List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion);
}
