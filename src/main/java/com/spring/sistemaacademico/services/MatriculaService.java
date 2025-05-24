package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    List<Matricula> findAll() throws Exception;

    Matricula save(Matricula matricula) throws Exception;

    Matricula update(Matricula matricula) throws Exception;

    Optional<Matricula> findById(Long id) throws Exception;

    void deleteById(Long id) throws Exception;

    void deleteAll() throws Exception;
}
