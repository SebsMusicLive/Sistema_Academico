package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Foro;

import java.util.Date;
import java.util.List;

@Service
public interface ForoService extends CrudService<Foro, Long> {

    List<Foro> findByTitulo(String titulo) throws Exception;

    List<Foro> findByDescripcion(String descripcion) throws Exception;

    List<Foro> findByFechaCreacion(Date fechaCreacion) throws Exception;

}
