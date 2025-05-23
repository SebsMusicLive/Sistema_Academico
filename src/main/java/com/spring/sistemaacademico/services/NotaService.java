package com.spring.sistemaacademico.services;
import sistemaAcademico.model.Nota;

import java.util.List;
import java.util.Optional;

public interface NotaService {

    // Guardar o actualizar una nota
    Nota guardarNota(Nota nota);

    // Obtener todas las notas
    List<Nota> obtenerTodasLasNotas();

    // Buscar una nota por su ID
    Optional<Nota> obtenerNotaPorId(Long id);

    // Eliminar una nota por su ID
    void eliminarNota(Long id);

    // Buscar notas por el ID del estudiante
    List<Nota> obtenerNotasPorEstudiante(Long idEstudiante);

    // Buscar notas por el ID de la evaluación
    List<Nota> obtenerNotasPorEvaluacion(Long idEvaluacion);
}
