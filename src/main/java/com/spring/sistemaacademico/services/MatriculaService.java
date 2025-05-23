package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Matricula;

import java.util.Optional;

@Service
public interface MatriculaService extends CrudService<Matricula, Long>{
    Matricula registrarMatricula(String estudianteId, String cursoId);
    void cancelarMatricula(String matriculaId);
    int consultarCuposDisponibles(String cursoId);
    boolean validarPrerrequisitos(String estudianteId, String cursoId);

    Optional<Matricula> findById(Long aLong) throws Exception;
}
