package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByTipo(String tipo);
    List<Evaluacion> findByPonderacion(float ponderacion);
    List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion);
}
