package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Nota;
import com.spring.sistemaacademico.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    // Guardar o actualizar una nota
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Nota guardarNota(@RequestBody Nota nota) {
        return notaService.guardarNota(nota);
    }

    // Obtener todas las notas
    @GetMapping
    public List<Nota> obtenerTodasLasNotas() {
        return notaService.obtenerTodasLasNotas();
    }

    // Obtener una nota por ID
    @GetMapping("/{id}")
    public Optional<Nota> obtenerNotaPorId(@PathVariable Long id) {
        return notaService.obtenerNotaPorId(id);
    }

    // Eliminar una nota por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarNota(@PathVariable Long id) {
        notaService.eliminarNota(id);
    }

    // Obtener notas por el ID del estudiante
    @GetMapping("/estudiante/{idEstudiante}")
    public List<Nota> obtenerNotasPorEstudiante(@PathVariable Long idEstudiante) {
        return notaService.obtenerNotasPorEstudiante(idEstudiante);
    }

    // Obtener notas por el ID de la evaluación
    @GetMapping("/evaluacion/{idEvaluacion}")
    public List<Nota> obtenerNotasPorEvaluacion(@PathVariable Long idEvaluacion) {
        return notaService.obtenerNotasPorEvaluacion(idEvaluacion);
    }
}
