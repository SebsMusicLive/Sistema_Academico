package com.spring.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaAcademico.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    //public List<Persona> findByNombre(String nombre);

    //public List<Persona> findByCorreo(String correo);

    //public List<Persona> findByTelefono(String telefono);

    //public List<Persona> findByDireccion(String direccion);

    //public List<Persona> findByFechaNacimiento(Date fechaNacimiento);

}
