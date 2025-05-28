package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Buscar cursos por carrera (verifica que Carrera tenga campo "id")
    List<Curso> findByCodigoCarrera_CodigoCarrera(Long codigoCarrera);

    // ✅ Buscar cursos por semestre (nombre correcto del campo ID)
    List<Curso> findByCodigoSemestre_CodigoSemestre(Long codigoSemestre);

    // Buscar cursos por docente (si Docente tiene campo "codigoDocente")
    List<Curso> findByCodigoDocente_CodigoDocente(Long codigoDocente);
}