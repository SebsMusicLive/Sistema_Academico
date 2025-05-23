package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Espacio;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.EspacioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl extends EspacioService {
    private final EspacioRepository repository;

    public List<Espacio> findAll() { return repository.findAll(); }
    public Espacio save(Espacio espacio) { return repository.save(espacio); }
    public Espacio update(Espacio espacio) { return repository.save(espacio); }
    public Semestre findById(Long id) { return repository.findById(id); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public void deleteAll() { repository.deleteAll(); }
}
