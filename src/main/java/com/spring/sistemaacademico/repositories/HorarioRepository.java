package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Curso;
import sistemaAcademico.model.Horario;

import java.util.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    public List<Horario> findByCodigoCurso(Curso codigoCurso);

    public List<Horario> findByHoraInicio(Date horaInicio);

    public List<Horario> findByHoraFin(Date horaFin);

    public List<Horario> findByTipoSesion(String tipoSesion);

}
