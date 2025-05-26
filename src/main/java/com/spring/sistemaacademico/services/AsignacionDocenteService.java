package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.exceptions.CargaExcesivaException;
import com.spring.sistemaacademico.exceptions.DisponibilidadException;
import com.spring.sistemaacademico.model.AsignacionDocente;
import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Docente;
import com.spring.sistemaacademico.model.Horario;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AsignacionDocenteService {

    List<AsignacionDocente> findAll();

    Optional<AsignacionDocente> findById(Long id);

    AsignacionDocente save(AsignacionDocente entity);

    AsignacionDocente update(AsignacionDocente entity);

    void deleteById(Long id);

    void deleteAll();

    List<AsignacionDocente> findByFecha(Date fecha);

    List<AsignacionDocente> findByCargaHoraria(int cargaHoraria);

    // Métodos nuevos que agregas, con excepciones indicadas
    void asignarCurso(Docente docente, Curso curso) throws DisponibilidadException, CargaExcesivaException;

    boolean verificarDisponibilidad(Docente docente, Horario horario);

    void ajustarCargaHoraria(Docente docente, int nuevaCarga) throws CargaExcesivaException;
}
