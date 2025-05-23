package com.spring.sistemaacademico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistemaAcademico.model.Calificacion;
import sistemaAcademico.model.Semestre;
import sistemaAcademico.repository.CalificacionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {


    private final CalificacionRepository repository;

    @Override
    public List<Calificacion> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Calificacion save(Calificacion calificacion) throws Exception {
        return repository.save(calificacion);
    }

    @Override
    public Calificacion update(Calificacion calificacion) throws Exception {
        return repository.save(calificacion);
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
    public List<Calificacion> findByNota(float nota) {
        return repository.findByNota(nota);
    }

    @Override
    public List<Calificacion> findByEstudianteId(Long estudianteId) {
        return repository.findByEstudianteId(estudianteId);
    }

}
