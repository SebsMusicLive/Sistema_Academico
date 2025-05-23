package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Asistencia;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AsistenciaService extends CrudService<Asistencia, Long> {

    List<Asistencia> findByFechaAsistencia(Date fechaAsistencia);

    List<Asistencia> findByAsistencia(boolean asistencia);
}
