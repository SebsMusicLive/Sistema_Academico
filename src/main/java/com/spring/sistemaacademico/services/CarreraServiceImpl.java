package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Carrera;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.CarreraRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarreraServiceImpl implements CarreraService {

    private final CarreraRepository repository;

    @Override
    public List<Carrera> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Carrera save(Carrera carrera) throws Exception {
        return repository.save(carrera);
    }

    @Override
    public Carrera update(Carrera carrera) throws Exception {
        return repository.save(carrera);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Override
    public List<Carrera> findByNombreCarrera(String nombreCarrera) {
        return repository.findByNombreCarrera(nombreCarrera);
    }

    @Override
    public List<Carrera> findByDuracion(int duracion) {
        return repository.findByDuracion(duracion);
    }
}
