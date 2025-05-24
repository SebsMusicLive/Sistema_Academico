package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Curso;
import com.spring.sistemaacademico.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByCodigoCurso(Curso codigoCurso);

    List<Horario> findByHoraInicio(Date horaInicio);

    List<Horario> findByHoraFin(Date horaFin);

    List<Horario> findByTipoSesion(String tipoSesion);
}
