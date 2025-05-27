package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.PrestamoRecurso;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public interface PrestamoRecursoService extends CrudService<PrestamoRecurso, Long> {

    List<PrestamoRecurso> findByFechaPrestamo(Date fechaPrestamo);

    List<PrestamoRecurso> findByFechaDevolucion(Date fechaDevolucion);

    @Override
    Optional<PrestamoRecurso> findById(Long id) throws Exception;
}