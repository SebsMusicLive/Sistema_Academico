package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Espacio;
import com.spring.sistemaacademico.repositories.EspacioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl implements EspacioService {

    private final EspacioRepository repository;

    @Override
    public List<Espacio> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Espacio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Espacio save(Espacio espacio) {
        return repository.save(espacio);
    }

    @Override
    public Espacio update(Espacio espacio) {
        return repository.save(espacio);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
