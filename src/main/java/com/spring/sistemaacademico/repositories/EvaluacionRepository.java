package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Evaluacion;

import java.util.Date;
import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    public List<Evaluacion> findByTipo(String tipo);

    public List<Evaluacion> findByPonderacion(float ponderacion);

    public List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion);


}
