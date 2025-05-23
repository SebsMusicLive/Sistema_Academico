package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Calificacion;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {


    List<Calificacion> findByEstudianteId(Long estudianteId);
    List<Calificacion> findByNota(float nota);

}
