package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {

    public List<Programa> findByNombre(String nombre);

    public List<Programa> findByDescripcion(String descripcion);
}
