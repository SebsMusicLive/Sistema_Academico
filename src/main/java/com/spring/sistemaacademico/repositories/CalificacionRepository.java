package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {


    List<Calificacion> findByEstudianteCodigoEstudiante(Long codigoEstudiante);
    List<Calificacion> findByNota(float nota);

}
