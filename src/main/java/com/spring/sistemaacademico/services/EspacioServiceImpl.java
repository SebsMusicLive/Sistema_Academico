package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Espacio;
import com.spring.sistemaacademico.model.Semestre;
import com.spring.sistemaacademico.repositories.EspacioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
