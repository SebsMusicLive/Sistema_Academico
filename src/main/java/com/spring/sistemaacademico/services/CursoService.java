package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Estudiante;
import com.spring.sistemaacademico.model.Horario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CursoService extends CrudService<Curso, Long> {

    List<Curso> findByCodigoCarreraId(Long codigoCarrera);

    List<Curso> findByCodigoSemestreId(Long codigoSemestre);

    List<Curso> findByCodigoDocenteId(Long codigoDocente);

    void inscribirEstudiante(Long idCurso, Estudiante estudiante);

    void agregarPrerrequisito(Long idCurso, Curso prerrequisito);

    boolean validarPrerrequisitos(Long codigoEstudiante, Long idCursoDestino);

    void agregarCurso(Curso curso);

    void eliminarCurso(Long idCurso);

    boolean validarCupos(Long idCurso);

    Horario generarHorario();

    boolean verificarDisponibilidad();

    Optional<Curso> findById(Long id); // Corregido para coincidir con el uso en el controlador
}