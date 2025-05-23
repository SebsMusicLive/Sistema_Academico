package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    //public List<Semestre> findByNumeroSemestre(int numeroSemestre);

    //public List<Semestre> findByFechaInicio(Date fechaInicio);

    //public List<Semestre> findByFechaFin(Date fechaFin);
}
