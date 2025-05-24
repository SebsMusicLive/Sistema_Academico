package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Nota;
import com.spring.sistemaacademico.repositories.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;

    @Override
    public Nota guardarNota(Nota nota) {
        return notaRepository.save(nota);
    }

    @Override
    public List<Nota> obtenerTodasLasNotas() {
        return notaRepository.findAll();
    }

    @Override
    public Optional<Nota> obtenerNotaPorId(Long id) {
        return notaRepository.findById(id);
    }

    @Override
    public void eliminarNota(Long id) {
        notaRepository.deleteById(id);
    }

    @Override
    public List<Nota> obtenerNotasPorEstudiante(Long idEstudiante) {
        return notaRepository.findByEstudianteId(idEstudiante);
    }

    @Override
    public List<Nota> obtenerNotasPorEvaluacion(Long idEvaluacion) {
        return notaRepository.findByEvaluacionId(idEvaluacion);
    }
}
