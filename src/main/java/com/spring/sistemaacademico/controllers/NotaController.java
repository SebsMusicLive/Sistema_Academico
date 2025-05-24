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
        Optional<Nota> nota = notaService.obtenerNotaPorId(id);
        return nota.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nota> create(@RequestBody Nota nota) {
        Nota nueva = notaService.guardarNota(nota);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Long id, @RequestBody Nota nota) {
        nota.setCodigoNota(id);
        Nota actualizada = notaService.guardarNota(nota);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
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
