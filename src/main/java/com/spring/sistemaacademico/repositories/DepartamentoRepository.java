package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Departamento;
import com.spring.sistemaacademico.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    List<Departamento> findByNombre(String nombre);
    List<Departamento> findByFacultad(Facultad facultad);
}
