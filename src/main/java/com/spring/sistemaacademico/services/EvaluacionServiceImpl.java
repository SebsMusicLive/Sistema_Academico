package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Evaluacion;
import com.spring.sistemaacademico.repositories.EvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository repository;

    @Override
    public List<Evaluacion> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Evaluacion save(Evaluacion evaluacion) throws Exception {
        return repository.save(evaluacion);
    }

    @Override
    public Evaluacion update(Evaluacion evaluacion) throws Exception {
        if (evaluacion.getCodigoEvaluacion() == null || !repository.existsById(evaluacion.getCodigoEvaluacion())) {
            throw new Exception("Evaluación no encontrada");
        }
        return repository.save(evaluacion);
    }

    @Override
    public Optional<Evaluacion> findById(Long id) throws Exception {
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
    public List<Evaluacion> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    @Override
    public List<Evaluacion> findByPonderacion(float ponderacion) {
        return repository.findByPonderacion(ponderacion);
    }

    @Override
    public List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion) {
        return repository.findByFechaEvaluacion(fechaEvaluacion);
    }
}
