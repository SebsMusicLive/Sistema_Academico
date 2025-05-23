package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Curso;
import sistemaAcademico.model.Estudiante;
import sistemaAcademico.model.Matricula;
import sistemaAcademico.repository.CursoRepository;
import sistemaAcademico.repository.EstudianteRepository;
import sistemaAcademico.repository.MatriculaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    @Override
    public Matricula registrarMatricula(String estudianteId, String cursoId) {
        Estudiante estudiante = estudianteRepository.findById(Long.valueOf(estudianteId))
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Curso curso = cursoRepository.findById(Long.valueOf(cursoId))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (!validarPrerrequisitos(estudianteId, cursoId)) {
            throw new RuntimeException("No cumple con los prerrequisitos del curso");
        }

        if (!curso.validarCupos()) {
            throw new RuntimeException("No hay cupos disponibles");
        }

        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setCodigoCurso((List<Curso>) curso);
        matricula.setFechaMatricula(LocalDate.now());

        curso.actualizarCupos(); // Asumimos que reduce el cupo
        return matriculaRepository.save(matricula);
    }

    @Override
    public void cancelarMatricula(String matriculaId) {
        Matricula matricula = matriculaRepository.findById(Long.valueOf(matriculaId))
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
        matriculaRepository.delete(matricula);
        // Lógica para liberar el cupo
    }

    @Override
    public int consultarCuposDisponibles(String cursoId) {
        Curso curso = cursoRepository.findById(Long.valueOf(cursoId))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        return curso.consultarCuposDisponibles(); // Este método puede estar en la entidad Curso
    }

    @Override
    public boolean validarPrerrequisitos(String estudianteId, String cursoId) {
        Estudiante estudiante = estudianteRepository.findById(Long.valueOf(estudianteId))
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Curso curso = cursoRepository.findById(Long.valueOf(cursoId))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Curso prerrequisito = curso.getCodigoPrerrequisito();
        if (prerrequisito == null) return true;

        return estudiante.generarReporteCursosAprobados().contains(prerrequisito);
    }

    @Override
    public List<Matricula> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Matricula save(Matricula matricula) throws Exception {
        return null;
    }

    @Override
    public Matricula update(Matricula matricula) throws Exception {
        return null;
    }

    @Override
    public Optional<Matricula> findById(Long aLong) throws Exception {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public void deleteAll() throws Exception {

    }
}
