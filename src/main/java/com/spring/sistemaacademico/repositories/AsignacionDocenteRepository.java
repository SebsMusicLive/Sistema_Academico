package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.AsignacionDocente;

import java.util.Date;
import java.util.List;
@Repository
public interface AsignacionDocenteRepository extends JpaRepository<AsignacionDocente, Long> {

    List<AsignacionDocente> findByFecha(Date fecha);

    List<AsignacionDocente> findByCargaHoraria(int cargaHoraria);
}
