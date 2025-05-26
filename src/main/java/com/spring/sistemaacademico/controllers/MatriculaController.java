package com.spring.sistemaacademico.controllers;

import com.spring.sistemaacademico.model.Matricula;
import com.spring.sistemaacademico.services.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll() throws Exception {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) throws Exception {
        return matriculaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody Matricula matricula) throws Exception {
        return ResponseEntity.status(201).body(matriculaService.save(matricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Long id, @RequestBody Matricula matricula) throws Exception {
        Optional<Matricula> optional = matriculaService.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        matricula.setCodigoMatricula(id);
        Matricula updated = matriculaService.update(matricula);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        Optional<Matricula> optional = matriculaService.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        matriculaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws Exception {
        matriculaService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
