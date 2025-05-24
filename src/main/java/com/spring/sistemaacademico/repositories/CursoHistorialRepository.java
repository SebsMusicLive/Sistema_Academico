package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.CursoHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoHistorialRepository extends JpaRepository<CursoHistorial, Long> {
    List<CursoHistorial> findByCalificacionFinal(float calificacionFinal);
    List<CursoHistorial> findByHistorialAcademico_Estudiante_CodigoEstudiante(Long codigoEstudiante);
}