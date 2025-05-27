package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Nota;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface NotaService {
    Nota guardarNota(Nota nota);
    List<Nota> obtenerTodasLasNotas();
    Optional<Nota> obtenerNotaPorId(Long id);
    void eliminarNota(Long id);
    List<Nota> obtenerNotasPorEstudiante(Long idEstudiante);
    List<Nota> obtenerNotasPorEvaluacion(Long idEvaluacion);
}
