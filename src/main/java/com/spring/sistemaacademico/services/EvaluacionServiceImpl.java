package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Evaluacion;
import com.spring.sistemaacademico.model.Semestre;
import com.spring.sistemaacademico.repositories.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Override
    public List<Evaluacion> findAll() throws Exception {
        return evaluacionRepository.findAll();
    }

    @Override
    public Evaluacion save(Evaluacion evaluacion) throws Exception {
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public Evaluacion update(Evaluacion evaluacion) throws Exception {
        if (evaluacion.getCodigo_evaluacion() == null ||
                !evaluacionRepository.existsById(evaluacion.getCodigo_evaluacion())) {
            throw new Exception("La evaluación no existe o no tiene un ID válido");
        }
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public Semestre findById(Long id) throws Exception {
        return evaluacionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        evaluacionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        evaluacionRepository.deleteAll();
    }

    @Override
    public List<Evaluacion> findByTipo(String tipo) {
        return evaluacionRepository.findByTipo(tipo);
    }

    @Override
    public List<Evaluacion> findByPonderacion(float ponderacion) {
        return evaluacionRepository.findByPonderacion(ponderacion);
    }

    @Override
    public List<Evaluacion> findByFechaEvaluacion(Date fechaEvaluacion) {
        return evaluacionRepository.findByFechaEvaluacion(fechaEvaluacion);
    }
}