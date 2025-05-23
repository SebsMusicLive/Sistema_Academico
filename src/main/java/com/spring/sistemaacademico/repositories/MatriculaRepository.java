package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    //public List<Matricula> findByFechaMatricula(Date fechaMatricula);
}
