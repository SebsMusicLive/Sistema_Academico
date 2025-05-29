package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByEstudianteCodigoEstudiante(Long codigoEstudiante);
    List<Nota> findByEvaluacionCodigoEvaluacion(Long codigoEvaluacion); // CORREGIDO
}