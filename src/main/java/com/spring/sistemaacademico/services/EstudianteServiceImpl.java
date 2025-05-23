package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.*;
import com.spring.sistemaacademico.repositories.CursoRepository;
import com.spring.sistemaacademico.repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteService estudianteService;
    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;


    @Override
    public List<Estudiante> findAll() throws Exception{
        return estudianteService.findAll();
    }

    @Override
    public Estudiante save(Estudiante estudiante) throws Exception{
        return estudianteRepository.save(estudiante);
    }

    public Estudiante update(Estudiante estudiante) throws Exception {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
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

        return estudiante.getHistorialAcademico().stream()
                .flatMap(h -> h.getCursoHistorial().stream())
                .map(CursoHistorial::getCurso)
                .toList();
    }

    @Override
    public void inscribirCurso(Long idEstudiante, Curso curso) {
        // Suponiendo que tienes una clase Matricula con lista de cursos
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Matricula matricula = estudiante.getMatricula();
        if (matricula != null) {
            matricula.getCodigoCurso().add(curso);
            // Suponiendo que hay repositorio para Matricula
            // matriculaRepository.save(matricula);
        }
    }

    @Override
    public void cancelarInscripcion(Long idEstudiante, Curso curso) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Matricula matricula = estudiante.getMatricula();
        if (matricula != null) {
            matricula.getCodigoCurso().remove(curso);
            // matriculaRepository.save(matricula);
        }
    }

    @Override
    public boolean validarPrerequisitos(Long idEstudiante, Curso curso) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Obtener todos los cursos aprobados del estudiante
        List<Curso> aprobados = estudiante.getHistorialAcademico().stream()
                .flatMap(h -> h.getCursoHistorial().stream())
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.APROBADO)
                .map(CursoHistorial::getCurso)
                .toList();

        // Validar que todos los prerequisitos estén en la lista de aprobados
        return curso.getPrerequisitos().stream().allMatch(aprobados::contains);
    }


    @Override
    public List<CursoHistorial> generarReporteCursosAprobados(Long idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<CursoHistorial> resultado = estudiante.getHistorialAcademico().stream()
                .flatMap(h -> h.getCursoHistorial().stream())
                .filter(curso -> curso.getEstadoCurso() == EstadoCurso.APROBADO)
                .collect(Collectors.toList());

        return resultado;
    }


    @Override
    public List<Curso> generarReporteCursosEnProceso(Long idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return estudiante.getHistorialAcademico().stream()
                .flatMap(h -> h.getCursoHistorial().stream())
                .filter(ch -> ch.getEstadoCurso() == EstadoCurso.EN_CURSO)
                .map(CursoHistorial::getCurso)
                .toList();
    }

}
