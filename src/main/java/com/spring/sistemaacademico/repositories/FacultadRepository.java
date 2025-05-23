package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {

    public List<Facultad> findByNombre(String nombre);
}
