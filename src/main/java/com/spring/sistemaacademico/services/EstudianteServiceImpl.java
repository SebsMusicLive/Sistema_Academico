package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.*;
import com.spring.sistemaacademico.repositories.CursoRepository;
import com.spring.sistemaacademico.repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAll() throws Exception {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante save(Estudiante estudiante) throws Exception {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante update(Estudiante estudiante) throws Exception {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Optional<Estudiante> findById(Long id) throws Exception {
        return estudianteRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        estudianteRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        estudianteRepository.deleteAll();
    }

    @Override
    public List<Estudiante> findByNombre(String nombre) {
        return estudianteRepository.findByNombre(nombre);
    }

    @Override
    public List<Estudiante> findByCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

    @Override
    public List<Estudiante> findByTelefono(String telefono) {
        return estudianteRepository.findByTelefono(telefono);
    }

    @Override
    public List<Curso> consultarHistorialAcademico(Long idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return estudiante.getHistorialAcademico()
                .getCursoHistorial()
                .stream()
                .map(CursoHistorial::getCurso)
                .collect(Collectors.toList());
    }

    @Override
    public void inscribirCurso(Long idEstudiante, Curso curso) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Matricula matricula = estudiante.getMatricula();
        if (matricula != null) {
            matricula.getCodigoCurso().add(curso);
            // Aquí deberías guardar la matrícula si existe el repositorio
        }
    }

    @Override
    public void cancelarInscripcion(Long idEstudiante, Curso curso) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Matricula matricula = estudiante.getMatricula();
        if (matricula != null) {
            matricula.getCodigoCurso().remove(curso);
        }
    }

    @Override
    public boolean validarPrerequisitos(Long idEstudiante, Curso curso) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<Curso> aprobados = estudiante.getHistorialAcademico()
                .getCursoHistorial()
                .stream()
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.APROBADO)
                .map(CursoHistorial::getCurso)
                .collect(Collectors.toList());

        return curso.getPrerequisitos().stream().allMatch(aprobados::contains);
    }

    @Override
    public List<CursoHistorial> generarReporteCursosAprobados(Long idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return estudiante.getHistorialAcademico()
                .getCursoHistorial()
                .stream()
                .filter(curso -> curso.getEstadoCurso() == EstadoCurso.APROBADO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Curso> generarReporteCursosEnProceso(Long idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return estudiante.getHistorialAcademico()
                .getCursoHistorial()
                .stream()
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.EN_CURSO)
                .map(CursoHistorial::getCurso)
                .collect(Collectors.toList());
    }
}
