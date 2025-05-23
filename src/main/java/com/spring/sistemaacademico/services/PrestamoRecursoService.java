package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.PrestamoRecurso;

import java.util.Date;
import java.util.List;

@Service
public interface PrestamoRecursoService extends CrudService<PrestamoRecurso, Long> {

    public List<PrestamoRecurso> findByFechaPrestamo(Date fechaPrestamo);

    public List<PrestamoRecurso> findByFechaDevolucion(Date fechaDevolucion);
}
