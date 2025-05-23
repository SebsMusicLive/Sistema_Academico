package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Evaluacion;
import com.spring.sistemaacademico.services.EvaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    /**
     * Obtiene la lista de todas las evaluaciones
     */
    @GetMapping
    public List<Evaluacion> getAllEvaluaciones() throws Exception {
        return evaluacionService.findAll();
    }

    /**
     * Obtiene una evaluación por su ID
     */
    @GetMapping("/{codigoEvaluacion}")
    public Optional<Evaluacion> getEvaluacionById(@PathVariable Long codigoEvaluacion) throws Exception {
        return evaluacionService.findById(codigoEvaluacion);
    }

    /**
     * Crea una nueva evaluación
     */
    @PostMapping
    public Evaluacion createEvaluacion(@RequestBody Evaluacion evaluacion) throws Exception {
        return evaluacionService.save(evaluacion);
    }

    /**
     * Actualiza una evaluación existente
     */
    @PutMapping("/{codigoEvaluacion}")
    public Evaluacion updateEvaluacion(@PathVariable Long codigoEvaluacion, @RequestBody Evaluacion evaluacion) throws Exception {
        evaluacion.setCodigo_evaluacion(codigoEvaluacion);
        return evaluacionService.update(evaluacion);
    }

    /**
     * Elimina una evaluación por ID
     */
    @DeleteMapping("/{codigoEvaluacion}")
    public void deleteEvaluacion(@PathVariable Long codigoEvaluacion) throws Exception {
        evaluacionService.deleteById(codigoEvaluacion);
    }

    /**
     * Elimina todas las evaluaciones
     */
    @DeleteMapping
    public void deleteAllEvaluaciones() throws Exception {
        evaluacionService.deleteAll();
    }

    /*
     * Búsquedas adicionales (por si luego decides implementarlas)
     */

    @GetMapping("/buscar/tipo")
    public List<Evaluacion> getByTipo(@RequestParam String tipo) throws Exception {
        return evaluacionService.findByTipo(tipo);
    }

    @GetMapping("/buscar/ponderacion")
    public List<Evaluacion> getByPonderacion(@RequestParam float ponderacion) throws Exception {
        return evaluacionService.findByPonderacion(ponderacion);
    }

    @GetMapping("/buscar/fecha")
    public List<Evaluacion> getByFechaEvaluacion(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaEvaluacion) throws Exception {
        return evaluacionService.findByFechaEvaluacion(fechaEvaluacion);
    }

}
