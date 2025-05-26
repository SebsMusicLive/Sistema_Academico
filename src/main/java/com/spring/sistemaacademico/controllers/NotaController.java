package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Nota;
import com.spring.sistemaacademico.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<Nota>> getAll() {
        return ResponseEntity.ok(notaService.obtenerTodasLasNotas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getById(@PathVariable Long id) {
        return notaService.obtenerNotaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nota> create(@RequestBody Nota nota) {
        if (nota == null || nota.getEstudiante() == null || nota.getEvaluacion() == null) {
            return ResponseEntity.badRequest().build();
        }
        Nota nueva = notaService.guardarNota(nota);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Long id, @RequestBody Nota nota) {
        Optional<Nota> notaExistente = notaService.obtenerNotaPorId(id);
        if (notaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (nota == null || nota.getEstudiante() == null || nota.getEvaluacion() == null) {
            return ResponseEntity.badRequest().build();
        }
        nota.setCodigoNota(id);
        Nota actualizada = notaService.guardarNota(nota);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Nota> nota = notaService.obtenerNotaPorId(id);
        if (nota.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notaService.eliminarNota(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<Nota>> getByEstudiante(@PathVariable Long idEstudiante) {
        return ResponseEntity.ok(notaService.obtenerNotasPorEstudiante(idEstudiante));
    }

    @GetMapping("/evaluacion/{idEvaluacion}")
    public ResponseEntity<List<Nota>> getByEvaluacion(@PathVariable Long idEvaluacion) {
        return ResponseEntity.ok(notaService.obtenerNotasPorEvaluacion(idEvaluacion));
    }
}
