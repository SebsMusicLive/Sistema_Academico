package com.spring.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Persona;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.PersonaRepository;

import java.util.List;

@Service
public abstract class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    // Método para registrar una nueva persona
    public void registrarDatos(Persona persona) throws Exception {
        personaRepository.save(persona);
    }

    // Método para actualizar los datos de una persona
    public void actualizarDatos(Persona persona) throws Exception {
        if (!personaRepository.existsById(persona.getDocumento())) {
            throw new Exception("No se puede actualizar: Persona no encontrada");
        }
        personaRepository.save(persona);
    }

    // Método para eliminar una persona por su ID (documento)
    public void eliminarDatos(Long id) throws Exception {
        if (!personaRepository.existsById(id)) {
            throw new Exception("No se puede eliminar: Persona no encontrada");
        }
        personaRepository.deleteById(id);
    }

    // Métodos del CrudService

    @Override
    public Persona save(Persona persona) throws Exception {
        return personaRepository.save(persona);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return personaRepository.findById(id)
                .orElseThrow(() -> new Exception("Persona no encontrada"));
    }

    @Override
    public List<Persona> findAll() throws Exception {
        return personaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona update(Persona persona) throws Exception {
        if (!personaRepository.existsById(persona.getDocumento())) {
            throw new Exception("No se puede actualizar: Persona no encontrada");
        }
        return personaRepository.save(persona);
    }
}
