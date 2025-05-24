package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Horario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HorarioService {

    List<Horario> findAll();

    Optional<Horario> findById(Long id);

    Horario save(Horario horario);

    Horario update(Horario horario) throws Exception;

    void deleteById(Long id);

    void deleteAll();

    List<Horario> findByCodigoCurso(Curso codigoCurso);

    List<Horario> findByHoraInicio(Date horaInicio);

    List<Horario> findByHoraFin(Date horaFin);

    List<Horario> findByTipoSesion(String tipoSesion);

    Horario modificarHorario(Long id, Date nuevaHoraInicio, Date nuevaHoraFin) throws Exception;

    Horario asignarHorario(Curso curso, Date horaInicio, Date horaFin, String tipoSesion);

    boolean verificarDisponibilidad(Curso curso, Date horaInicio, Date horaFin);

    List<String> optimizarUsoRecursos();
}
