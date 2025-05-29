package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Evaluacion;
import com.spring.sistemaacademico.services.EvaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<Evaluacion>> getAll() throws Exception {
        List<Evaluacion> evaluaciones = evaluacionService.findAll();
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getById(@PathVariable Long id) throws Exception {
        Optional<Evaluacion> evaluacion = evaluacionService.findById(id);
        return evaluacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evaluacion> save(@RequestBody Evaluacion evaluacion) throws Exception {
        Evaluacion saved = evaluacionService.save(evaluacion);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> update(@PathVariable Long id, @RequestBody Evaluacion evaluacionActualizada) throws Exception {
        Optional<Evaluacion> existente = evaluacionService.findById(id);
        if (existente.isPresent()) {
            evaluacionActualizada.setCodigoEvaluacion(id); // cambio de setter aquí
            return ResponseEntity.ok(evaluacionService.update(evaluacionActualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        if (evaluacionService.findById(id).isPresent()) {
            evaluacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Evaluacion>> getByTipo(@PathVariable String tipo) {
        List<Evaluacion> result = evaluacionService.findByTipo(tipo);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/ponderacion/{ponderacion}")
    public ResponseEntity<List<Evaluacion>> getByPonderacion(@PathVariable float ponderacion) {
        List<Evaluacion> result = evaluacionService.findByPonderacion(ponderacion);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Evaluacion>> getByFechaEvaluacion(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        List<Evaluacion> result = evaluacionService.findByFechaEvaluacion(fecha);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }
}