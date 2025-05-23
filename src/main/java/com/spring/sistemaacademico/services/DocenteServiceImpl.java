package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.*;
import sistemaAcademico.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;
    private final CursoRepository cursoRepository;
    private final EvaluacionRepository evaluacionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CalificacionRepository calificacionRepository;

    @Override
    public List<Docente> findAll() throws Exception {
        return docenteRepository.findAll();
    }

    @Override
    public Docente save(Docente docente) throws Exception {
        return docenteRepository.save(docente);
    }

    @Override
    public Docente update(Docente docente) throws Exception {
        return docenteRepository.save(docente);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return docenteRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        docenteRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        docenteRepository.deleteAll();
    }

    @Override
    public List<Docente> findByNombre(String nombre) {
        return docenteRepository.findByNombre(nombre);
    }

    @Override
    public List<Docente> findByCorreo(String correo) {
        return docenteRepository.findByCorreo(correo);
    }

    @Override
    public List<Docente> findByTelefono(String telefono) {
        return docenteRepository.findByTelefono(telefono);
    }


    public void asignarCurso(Long idDocente, Curso curso) {
        Docente docente = docenteRepository.findById(idDocente)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));

        if (validarDisponibilidad(docente)) {
            curso.setCodigoDocente(docente);
            cursoRepository.save(curso);
        } else {
            throw new IllegalStateException("El docente no tiene disponibilidad para más cursos");
        }
    }

    public void generarEvaluacion(Long idDocente, Curso curso) {
        Docente docente = docenteRepository.findById(idDocente)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));

        if (!curso.getCodigoDocente().getCodigoDocente().equals(idDocente)) {
            throw new IllegalArgumentException("El curso no pertenece al docente");
        }

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setCodigoCurso(curso);
        // Puedes añadir más campos como fecha, tipo, ponderación, etc.

        evaluacionRepository.save(evaluacion);
    }

    public void registrarCalificacion(Estudiante estudiante, Evaluacion evaluacion, float nota) {
        Curso curso = evaluacion.getCodigoCurso();
        Docente docente = curso.getCodigoDocente();

        if (!docente.getCursos().contains(curso)) {
            throw new IllegalArgumentException("El docente no está asignado al curso de esta evaluación");
        }

        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setCodigoEstudiante(estudiante);
        nuevaCalificacion.setCodigo_evaluacion(evaluacion);
        nuevaCalificacion.setNota(nota);

        calificacionRepository.save(nuevaCalificacion);
    }

    public boolean validarDisponibilidad(Docente docente) {
        int cargaTotal = 0;
        List<Curso> cursos = cursoRepository.findByCodigoDocente_CodigoDocente(docente.getCodigoDocente());
        for (Curso c : cursos) {
            cargaTotal += c.getHorasTeoricas() + c.getHorasPracticas();
        }
        return cargaTotal < docente.getCargaHoraria();
    }

    public List<Curso> generarReporteCursosAsignados(Long idDocente) {
        return cursoRepository.findByCodigoDocente_CodigoDocente(idDocente);
    }

    public List<Evaluacion> historialEvaluaciones(Long idDocente) {
        List<Evaluacion> historial = new ArrayList<>();
        List<Curso> cursos = cursoRepository.findByCodigoDocente_CodigoDocente(idDocente);
        for (Curso curso : cursos) {
            if (curso.getEvaluacion() != null) {
                historial.add(curso.getEvaluacion());
            }
        }
        return historial;
    }
}

