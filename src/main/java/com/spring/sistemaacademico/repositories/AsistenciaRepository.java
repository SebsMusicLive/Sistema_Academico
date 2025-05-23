package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Asistencia;

import java.util.Date;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    //public List<Asistencia> findByFechaAsistencia(Date fechaAsistencia);
    //public List<Asistencia> findByAsistencia(boolean asistencia);

    List<Asistencia> findByFechaAsistencia(Date fechaAsistencia);
    List<Asistencia> findByAsistencia(boolean asistencia);

}
