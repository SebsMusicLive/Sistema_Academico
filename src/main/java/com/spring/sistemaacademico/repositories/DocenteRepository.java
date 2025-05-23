package com.spring.sistemaacademico.repositories;

import com.spring.sistemaacademico.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    List<Docente> findByNombre(String nombre);
    List<Docente> findByCorreo(String correo);
    List<Docente> findByTelefono(String telefono);
}
