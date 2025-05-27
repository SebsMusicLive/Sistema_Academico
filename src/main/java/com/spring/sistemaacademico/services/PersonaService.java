package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonaService extends CrudService<Persona, Long> {

}
