package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Docente;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    List<Docente> findByNombre(String nombre);
    List<Docente> findByCorreo(String correo);
    List<Docente> findByTelefono(String telefono);
}
