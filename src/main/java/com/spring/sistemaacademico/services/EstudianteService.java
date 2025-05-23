package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Curso;
import sistemaAcademico.model.CursoHistorial;
import sistemaAcademico.model.Estudiante;

import java.util.List;

@Service
public interface EstudianteService extends CrudService <Estudiante, Long>{

    List<Estudiante> findByNombre(String nombre);

    List<Estudiante> findByCorreo(String correo);

    List<Estudiante> findByTelefono(String telefono);

    List<Curso> consultarHistorialAcademico(Long idEstudiante);

    void inscribirCurso(Long idEstudiante, Curso curso);

    void cancelarInscripcion(Long idEstudiante, Curso curso);

    boolean validarPrerequisitos(Long idEstudiante, Curso curso);

    List<CursoHistorial> generarReporteCursosAprobados(Long idEstudiante);

    List<Curso> generarReporteCursosEnProceso(Long idEstudiante);


}
