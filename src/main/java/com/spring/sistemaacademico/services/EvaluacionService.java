package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Evaluacion;

import java.util.Date;
import java.util.List;

@Service
public interface EvaluacionService extends CrudService<Evaluacion, Long> {

    public List<Evaluacion> findByTipo(String tipo);

    public List<Evaluacion> findByPonderacion(float ponderacion);

    public List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion);


}
