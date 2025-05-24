package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Asistencia;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Date;
import java.util.List;

@Service
public interface AsistenciaService extends CrudService<Asistencia, Long> {

    List<Asistencia> findAll();

    Optional<Asistencia> findById(Long id); // <--- Asegúrate de incluir esta línea

    Asistencia save(Asistencia asistencia);

    void deleteById(Long id);

    List<Asistencia> findByFechaAsistencia(Date fecha);

    List<Asistencia> findByAsistencia(boolean asistencia);
}
