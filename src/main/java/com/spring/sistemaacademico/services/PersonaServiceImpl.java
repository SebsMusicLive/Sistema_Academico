package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Persona;
import com.spring.sistemaacademico.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona save(Persona persona) throws Exception {
        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> findById(Long id) throws Exception {
        return personaRepository.findById(id);
    }

    @Override
    public List<Persona> findAll() throws Exception {
        return personaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (!personaRepository.existsById(id)) {
            throw new Exception("No se puede eliminar: Persona no encontrada");
        }
        personaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        personaRepository.deleteAll();
    }

    @Override
    public Persona update(Persona persona) throws Exception {
        if (persona.getDocumento() == null || !personaRepository.existsById(persona.getDocumento())) {
            throw new Exception("No se puede actualizar: Persona no encontrada");
        }
        return personaRepository.save(persona);
    }
}
