package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.AsignacionDocente;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface AsignacionDocenteService {

    List<AsignacionDocente> findAll();

    Optional<AsignacionDocente> findById(Long id);

    AsignacionDocente save(AsignacionDocente entity);

    AsignacionDocente update(AsignacionDocente entity);

    void deleteById(Long id);

    void deleteAll();

    List<AsignacionDocente> findByFecha(Date fecha);

    List<AsignacionDocente> findByCargaHoraria(int cargaHoraria);
}
