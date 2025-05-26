package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.PrestamoRecurso;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrestamoRecursoService extends CrudService<PrestamoRecurso, Long> {

    List<PrestamoRecurso> findByFechaPrestamo(Date fechaPrestamo);

    List<PrestamoRecurso> findByFechaDevolucion(Date fechaDevolucion);

    @Override
    Optional<PrestamoRecurso> findById(Long id) throws Exception;
}