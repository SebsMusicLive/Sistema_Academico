package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    //public List<Matricula> findByFechaMatricula(Date fechaMatricula);
}
