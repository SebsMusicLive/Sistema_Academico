package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Estudiante;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByNombre(String nombre);
    List<Estudiante> findByCorreo(String correo);
    List<Estudiante> findByTelefono(String telefono);
}
