package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.PrestamoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrestamoRecursoRepository extends JpaRepository<PrestamoRecurso, Long> {

    public List<PrestamoRecurso> findByFechaPrestamo(Date fechaPrestamo);

    public List<PrestamoRecurso> findByFechaDevolucion(Date fechaDevolucion);
}
