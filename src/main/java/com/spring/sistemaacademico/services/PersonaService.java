package com.spring.sistemaacademico.services;

import org.springframework.stereotype.Service;
import sistemaAcademico.model.Persona;

@Service
public interface PersonaService extends CrudService<Persona, Long> {

    //public List<Persona> findByNombre(String nombre);

    //public List<Persona> findByCorreo(String correo);

    //public List<Persona> findByTelefono(String telefono);

    //public List<Persona> findByDireccion(String direccion);

    //public List<Persona> findByFechaNacimiento(Date fechaNacimiento);

}
