package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.AsignacionDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AsignacionDocenteRepository extends JpaRepository<AsignacionDocente, Long> {

    List<AsignacionDocente> findByFecha(Date fecha);

    List<AsignacionDocente> findByCargaHoraria(int cargaHoraria);
}
