package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Asistencia;

import java.util.Date;
import java.util.List;

@Service
public interface AsistenciaService extends CrudService<Asistencia, Long> {

    List<Asistencia> findByFechaAsistencia(Date fechaAsistencia);

    List<Asistencia> findByAsistencia(boolean asistencia);
}
